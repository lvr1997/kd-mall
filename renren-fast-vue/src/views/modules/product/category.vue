<template>
  <div>
    <el-switch
      v-model="draggable"
      active-text="开启拖拽"
      inactive-text="关闭拖拽"></el-switch>
    <el-button v-if="draggable" size="small" @click="batchSave">批量保存</el-button>
    <el-button type="danger" size="small" @click="batchDelete">批量删除</el-button>
    <el-tree :data="category" :props="defaultProps"
              @node-click="handleNodeClick"
              node-key="catId"
              :expand-on-click-node="false"
              :default-expanded-keys="expandedKey"
              :draggable="draggable"
              :allow-drop="allowDrop"
              @node-drop="handleDrop"
              show-checkbox
              ref="categoryTree">
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if="node.level <=2" type="text" size="mini" @click="() => append(data)">Append</el-button>
          <el-button type="text" size="mini" @click="() => edit(data)">edit</el-button>
          <el-button
          v-if="node.childNodes.length ==0"
          type="text"
          size="mini"
          @click="() => remove(node, data)">
          Delete</el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog :title="title" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <el-form :model="productCategory">
        <el-form-item label="分类名称" >
          <el-input v-model="productCategory.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="图标" >
          <el-input v-model="productCategory.icon" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="计量单位" >
          <el-input v-model="productCategory.productUnit" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitData">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        pCid: [],
        draggable: false,
        updateNodes:[],
        maxLevel:0,
        title: '',
        dialogType: "",
        productCategory: {
          name: '',
          parentCid:0,
          catLevel:0,
          showStatus:1,
          sort:0,
          catId: null,
          icon: '',
          productUnit: '',
        },
        dialogFormVisible: false,
        category: [],
        expandedKey: [],
        defaultProps: {
          children: 'children',
          label: 'name',
          dialogVisible: false
        }
      }
    },
    created () {
      this.getCategory()
    },
    methods: {
      handleNodeClick (data) {
        // console.log(data);
      },
      getCategory () {
        this.$http({
          url: this.$http.adornUrl('/product/category/list'),
          method: 'get'
        }).then((res) => {
          this.category = res.data.data
        })
      },
      batchSave() {
        this.$http({
          url: this.$http.adornUrl('/product/category/update/sort'),
          method: 'post',
          data: this.$http.adornData(this.updateNodes, false)
        }).then(({data}) => {
          this.$message({
            type: 'success',
            message: '分类顺序成功!',
          })
          this.getCategory()
          this.expandedKey = this.pCid
          this.updateNodes = []
          this.maxLevel = 0
        })
      },
      batchDelete(){
        let catIds = []
        let catNames = []
        var checkedNodes = this.$refs.categoryTree.getCheckedNodes()
        console.log("checkedNodes", checkedNodes)
        for (let i = 0; i < checkedNodes.length; i++) {
          catIds.push(checkedNodes[i].catId)
          catNames.push(checkedNodes[i].name)
        }
        this.$confirm(`此操作将永久删除【${catNames}】分类, 是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/product/category/delete'),
            method: 'post',
            data: this.$http.adornData(catIds, false)
          }).then(({data}) => {
            this.$message({
              type: 'success',
              message: '批量删除成功!'
            })
            this.getCategory()
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      allowDrop(draggingNode, dropNode, type) {
        this.countNodeLevel(draggingNode);
        //被拖动的当前节点以及所在的父节点总层数不能大于3
        let deep = Math.abs(this.maxLevel-draggingNode.level)+1;
        if(type=="inner"){
          return (deep+dropNode.level)<=3
        }else {
           return (deep+dropNode.parent.level)<=3
        }
      },
      countNodeLevel(draggingNode){
        //找出所有的子节点 求出最大深度
        if(draggingNode.childNodes != null && draggingNode.childNodes.length >0){
          for(let i=0;i<draggingNode.childNodes.length;i++){
            if(draggingNode.childNodes[i].level > this.maxLevel){
              this.maxLevel=draggingNode.childNodes[i].level
            }
            this.countNodeLevel(draggingNode.childNodes[i])
          }
        }
      },
      handleDrop(draggingNode, dropNode, dropType, ev) {
        console.log('tree drop: ', dropNode.label, dropType);
        //1.当前节点最新父节点id
        let pCid=0;
        let siblings=null;
        if(dropType=='before' || dropType=="after"){
          pCid=dropNode.parent.data.catId== undefined?0:dropNode.parent.data.catId
          siblings=dropNode.parent.childNodes;
        }else {
          pCid=dropNode.data.catId
          siblings=dropNode.childNodes
        }
        this.pCid.push(pCid)
        //2 当前拖拽节点的最先顺序
        for(let i=0;i<siblings.length;i++){
          if(siblings[i].data.catId ==draggingNode.data.catId){
            let catLevel = draggingNode.level
            if(siblings[i].level != draggingNode.level){
              //当前节点的层级发生变换
              catLevel = siblings[i].level
              //修改他子节点的层级
              this.updateChildNodeLevel(siblings[i])
            }
             this.updateNodes.push({catId: siblings[i].data.catId,sort:i,parentCid:pCid, catLevel: catLevel })
          }else {
            this.updateNodes.push({catId: siblings[i].data.catId,sort:i})
          }
        }

        console.log("updateNodes", this.updateNodes)
      },
      updateChildNodeLevel(node) {
        if(node.childNodes.length > 0) {
          for(let i=0; i< node.childNodes.length; i++) {
            var cNode = node.childNodes[i].data
            this.updateNodes.push({catId:cNode.catId, catLevel: node.childNodes[i].level})
            this.updateChildNodeLevel(node.childNodes[i])
          }
        }
      },
      append (data) {
        console.log(data)
        this.title = '添加分类'
        this.dialogType = 'add'
        this.dialogFormVisible=true
        this.productCategory.parentCid = data.catId
        this.productCategory.catLevel = data.catLevel * 1 + 1
        /* 清空修改的数据*/
        this.productCategory.name = ""
        this.productCategory.catId = null
        this.productCategory.icon = ""
        this.productCategory.productUnit =""
      },
      edit (data) {
        this.title = '修改分类'
        this.dialogType = 'edit'
        this.dialogFormVisible=true
        this.$http({
          url: this.$http.adornUrl('/product/category/info/' + data.catId),
          method: 'get',
        }).then(({data}) => {
          console.log(data)
          this.productCategory.name = data.category.name
          this.productCategory.catId = data.category.catId
          this.productCategory.icon = data.category.icon
          this.productCategory.productUnit = data.category.productUnit
          this.productCategory.parentCid = data.category.parentCid
        })
      },
      submitData() {
        if(this.dialogType == 'add') {
          this.addCategory()
        }
        if(this.dialogType == 'edit') {
          this.updateCategory()
        }
      },
      addCategory(){
        this.$http({
          url: this.$http.adornUrl('/product/category/save'),
          method: 'post',
          data: this.$http.adornData(this.productCategory, false)
        }).then(({data}) => {
          this.$message({
            type: 'success',
            message: '添加成功!',
          })
          this.dialogFormVisible=false
          this.getCategory()
          this.expandedKey = [this.productCategory.parentCid]
        })
      },
      updateCategory() {
        var {catId, name, icon, productUnit} = this.productCategory;
        this.$http({
          url: this.$http.adornUrl('/product/category/update'),
          method: 'post',
          data: this.$http.adornData({catId, name, icon, productUnit}, false)
        }).then(({data}) => {
          this.$message({
            type: 'success',
            message: '修改成功!',
          })
          this.dialogFormVisible=false
          this.getCategory()
          this.expandedKey = [this.productCategory.parentCid]
        })
      },
      remove (node, data) {
        var ids = [data.catId]
        this.$confirm(`此操作将永久删除【${data.name}】分类, 是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http({
            url: this.$http.adornUrl('/product/category/delete'),
            method: 'post',
            data: this.$http.adornData(ids, false)
          }).then(({data}) => {
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
            this.getCategory()
            this.expandedKey = [node.parent.data.catId]
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      }
    }
  }
</script>

<style>
  .custom-tree-node {
      flex: 1;
      display: flex;
      align-items: center;
      justify-content: space-between;
      font-size: 14px;
      padding-right: 8px;
  }
</style>
