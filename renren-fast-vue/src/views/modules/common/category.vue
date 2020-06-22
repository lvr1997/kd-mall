<template>
  <el-tree :data="category" :props="defaultProps" accordion
            @node-click="handleNodeClick"
            node-key="catId"
            ref="categoryTree" style="border-right: 1px solid #ccc;">
  </el-tree>
</template>

<script>
  export default {
    data () {
      return {
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
    methods: {
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
