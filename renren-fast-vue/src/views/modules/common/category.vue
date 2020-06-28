<template>
  <div>
    <el-input
      placeholder="输入关键字进行过滤"
      v-model="filterText">
    </el-input>
    <el-tree :data="category" :props="defaultProps" accordion
              @node-click="handleNodeClick"
              node-key="catId"
              :filter-node-method="filterNode"

              ref="categoryTree" style="border-right: 1px solid #ccc;">
    </el-tree>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        filterText: '',
        category: [],
        defaultProps: {
          children: 'children',
          label: 'name',
        }
      }
    },
    created () {
      this.getCategory()
    },
    watch: {
      filterText(val) {
        this.$refs.categoryTree.filter(val);
      }
    },
    methods: {
      filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
      },
      handleNodeClick(data, node) {
        // console.log(data)
        this.$emit('tree-node-click', data, node)
      },
      getCategory () {
        this.$http({
          url: this.$http.adornUrl('/product/category/list'),
          method: 'get'
        }).then((res) => {
          this.category = res.data.data
        })
      }
    }
  }
</script>

<style>
</style>
