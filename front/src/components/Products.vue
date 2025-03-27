<template>
  <a-typography-title class="title">商品一覧</a-typography-title>
  <a-table :dataSource="dataSource" :columns="columns">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'productId'">
        <span> <FieldNumberOutlined />&nbsp; &nbsp;商品番号 </span>
      </template>
      <template v-if="column.key === 'stockQuantity'">
        <span> <StockOutlined />&nbsp; &nbsp;在庫 </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
        <a>
          {{ record.name }}
        </a>
      </template>
      <template v-else-if="column.key === 'size'">
        <span>
          <a-tag :color="getColors(record.size)" class="tag"> {{ record.size }}</a-tag>
        </span>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a>Invite 一 {{ record.name }}</a>
          <a-divider type="vertical" />
          <a>Delete</a>
          <a-divider type="vertical" />
          <a class="ant-dropdown-link">
            More actions
            <down-outlined />
          </a>
        </span>
      </template>
    </template>
  </a-table>
</template>

<script setup lang="ts">
import { getAllProducts } from '@/api/api'
import { FieldNumberOutlined, StockOutlined } from '@ant-design/icons-vue'
import { h, onMounted, ref } from 'vue'

const dataSource = ref([])

const columns = ref([
  { title: '商品番号', dataIndex: 'productId', key: 'productId' },
  {
    title: '商品名',
    dataIndex: 'productName',
    key: 'productName',
  },
  {
    title: 'カテゴリー',
    dataIndex: 'category',
    key: 'category',
  },
  {
    title: '色',
    dataIndex: 'color',
    key: 'color',
  },
  {
    title: 'サイズ',
    dataIndex: 'size',
    key: 'size',
    customRender: (text: string) => {
      return h('a-tag', { color: '#f50' }, text)
    },
  },
  {
    title: '説明',
    dataIndex: 'description',
    key: 'description',
  },
  {
    title: '在庫',
    dataIndex: 'stockQuantity',
    key: 'stockQuantity',
  },
  {
    title: '更新時期',
    dataIndex: 'updatedAt',
    key: 'updatedAt',
  },
  {
    title: '入荷時期',
    dataIndex: 'createdAt',
    key: 'createdAt',
  },
])

onMounted(() => {
  getAllProducts().then((response) => {
    console.log('response.data: ', response.data)
    dataSource.value = response.data
  })
})

const getColors = (size: string) => {
  if (size == 'S') {
    return '#f50'
  }
  if (size == 'M') {
    return '#2db7f5'
  }
  if (size == 'L') {
    return '#87d068'
  }
  if (size == 'One Size') {
    return 'purple'
  }
}
</script>

<style scoped lang="scss">
.title {
  display: flex;
  margin-top: -15px;
}
.tag {
  display: inline-block; /* 使标签根据内容自适应宽度 */
  min-width: 50px; /* 设置最小宽度为 40px + 10px */
  padding: 0 10px; /* 添加左右内边距 */
  text-align: center; /* 文本居中 */
}
</style>
