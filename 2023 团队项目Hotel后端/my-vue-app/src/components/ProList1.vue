<!-- <script setup>
// import TwoDemo from './components/TwoDemo.vue'
const msg='从父亲向儿子传值'
</script>

<template>

    <router-link to="/test2">点我|</router-link>
    <router-view> </router-view>
</template>




<style scoped>
</style> -->




<template>

<div class="topTool">
      <el-input v-model="sname" placeholder="请输入姓名搜索"
 @input="handleSearchName" :prefix-icon="Search">
      </el-input>
      <el-button type="primary" :icon="Plus" @click="handleAdd" 
style="margin-left: 20px;">添加数据</el-button>

<el-button type="danger" :icon="Delete" @click="handleDelList" 
v-if="multipleSelection.length>0">删除选中数据</el-button>


 </div>




    <!--el-table数据表格组件-->
      <el-table
          :data="displayedItems"
          @sort-change="handleSortChange"
          :header-cell-style="{ background: '#f6f9fa'}" 
          empty-text="暂无数据"
      @selection-change="handleSelectionChange">
 
          >
          <el-table-column type="selection"/>
        <!--el-table-column列-->
        <el-table-column prop="id" label="ID" width="80" sortable></el-table-column>
        <el-table-column prop="name" label="姓名" sortable></el-table-column>
        <el-table-column prop="birth" label="出生日期"></el-table-column>
        <el-table-column label="操作" min-width="180">
          <template #default="{ row }">
            <el-button type="primary" size="small" icon="Edit"  
    @click="handleEdit(row)">编辑</el-button>
            <el-button type="danger" size="small" :icon="Delete" 
    @click="handleDelete(row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>


      <el-pagination
    background
    :current-page="currentPage"
    :page-size="pageSize"
    :total="total"
    layout="prev, pager, next"
    @current-change="handleCurrentChange"
/>





<el-dialog v-model="dialogFormVisible" :title="dialogTitle">
    <el-form :model="tableform">
      <el-form-item label="姓名" :label-width="100">
        <el-input v-model="tableform.name" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="出生年月" :label-width="100">
        <el-date-picker
            v-model="tableform.birth"
            type="date"
            placeholder="选择日期"
            format="YYYY-MM-DD"
            value-format="YYYY-MM-DD"
        />
      </el-form-item>
    </el-form>
    <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="dialogOk">
        确定
      </el-button>
    </span>
    </template>
  </el-dialog>



    </template>
    

<script setup>

import {ref,reactive,computed} from "vue";
import { ElMessage, ElMessageBox } from 'element-plus'

        //使用:icon绑定图标，需单独导入图标组件
import { Plus,Delete,Edit,Search } from '@element-plus/icons-vue'
//定义用户信息数据
const userData = [
  { id: 1, name: '葛秀兰', birth: '1999-07-12' },
  { id: 2, name: '王淑华', birth: '1986-08-27' },
  { id: 3, name: '陈明明', birth: '1998-04-24' },
  { id: 4, name: '吴海燕', birth: '1993-05-03' },
  { id: 5, name: '高建军', birth: '1981-01-18' },
  { id: 6, name: '孙晓慧', birth: '1992-11-08' },
  { id: 7, name: '赵红梅', birth: '1984-03-17' },
  { id: 8, name: '徐卫东', birth: '2002-06-20' },
  { id: 9, name: '黄艳华', birth: '1987-09-26' },
  { id: 10, name: '张永红', birth: '1995-12-28' }
]
function handleEdit(row) { // row 为当前行的数据
  dialogFormVisible.value = true // 设置对话框显示状态为 true 
  tableform.value = {...row} //表单数据设置为传入行数据
  dialogType.value = 'edit' // 设置对话框类型为 "edit"，方便修改弹窗标题
  
}



const handleDelete=(row)=> {
  ElMessageBox.confirm(
      '您确定要删除姓名为【' + row.name + '】的数据吗?', '危险操作',
      {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning', }
  )
      .then(() => {
        delrow(row)
        ElMessage({type: 'success', message: '完成删除！', })
      })
      .catch(() => {
        ElMessage({type: 'info', message: '取消删除!', })
      })
}




let currentPage = 1;// 定义当前页码初始值为1
const pageSize = 5;// 定义每页显示的数据条数为5
let total = ref(userData.length);// 初始化数据总条数
let displayedItems = ref(userData.slice(0, pageSize));//初始化当前页显示数据


function handleCurrentChange(newPage) {
  currentPage = newPage
  const startIndex = (newPage - 1) * pageSize
  displayedItems.value = userData.slice(startIndex, startIndex + pageSize)
  itemsCopy=displayedItems.value
}


const handleSearchName = (val) => { 
  if (val.length > 0) { // 搜索框有输入
    // 根据姓名进行过滤操作
    displayedItems.value = userData.filter(item => item.name.match(val)) 
  } else { // 搜索框无输入
    displayedItems.value = itemsCopy //还原数据表格
  }
}




// 定义 handleSortChange 方法，接收 sortData 参数
const handleSortChange = (sortData) => {
  // 解构 sortData 参数，得到 prop 和 order 两个属性
  const { prop, order } = sortData;
  // 使用 sort 方法对 displayedItems 数组进行排序，并更新其值
  displayedItems.value = displayedItems.value.sort((a, b) => {
    if (order === 'ascending') { // 升序排序
      return a[prop] > b[prop] ? 1 : -1;
    } else { // 降序排序
      return a[prop] < b[prop] ? 1 : -1;
    }
  });
}


let itemsCopy = displayedItems.value;
console.log(itemsCopy)
let sname = ref('')//初始化搜索框的值





const dialogFormVisible = ref(false)//初始化弹窗不显示
let tableform = ref({})//初始化弹窗表单数据
let dialogType = ref('add')//初始化弹窗类型为增加弹窗
const dialogTitle = computed(() => {//设置弹窗的标题
  return dialogType.value === 'add' ? '新增数据' : '编辑数据'
})


const handleAdd = () => { //箭头函数
  dialogFormVisible.value = true // 设置对话框的显示状态为 true
  dialogType.value = 'add' // 设置对话框的类型为 "add"
  tableform.value = {} // 清空表单数据
}

const dialogOk = () => { // 定义一个名为 dialogOk 的箭头函数
  dialogFormVisible.value = false // 设置对话框的显示状态为 false，将其关闭
  if (dialogType.value === 'add') { // 如果对话框的类型为 "add"
    userData.unshift({ // 在数据数组的开头添加一个新的数据项
      id: userData.length + 1, // 分配新的 ID 值
      ...tableform.value // 将表单数据添加到数据项中
    } 
    )
    
    total.value = userData.length // 更新数据总数
    displayedItems.value = userData.slice(0, pageSize) // 更新显示的数据项
  } 

  else { // 如果对话框的类型不是 "add"，即用户要修改数据
   // 在当前显示的数据项中查找要修改的数据的索引
    let index = displayedItems.value.findIndex(item => 
item.id === tableform.value.id)    
displayedItems.value[index] = tableform.value // 用表单数据更新对应的数据项
}

}

const delrow = (row) => { // 定义一个函数，用于删除某行数据
  let index = displayedItems.value.findIndex(item => item.id === row.id) // 找到当前行在 displayedItems 数组中的索引
  userData.splice(index, 1) // 从 users 数组中删除当前行的数据
  total.value = total.value - 1 // 更新数据总条数
  handleCurrentChange(currentPage) //重新渲染表格数据
}

// 创建响应式变量multipleSelection，用于存储当前选中的数据行
let multipleSelection = ref([]) 
// 处理表格行选中状态变化的方法，val是当前选中的数据行数组
const handleSelectionChange = (val) => {
  // 清空数组，确保每次更新都是最新选中状态
  multipleSelection.value = [] 
  val.forEach(item => { // 遍历选中的数据行数组
    multipleSelection.value.push(item) // 将选中的每一行数据存入multipleSelection数组中
  })
}

const handleDelList = () => {
  ElMessageBox.confirm(
      '您确定要删除选择的数据吗?',
      '危险操作',
      {confirmButtonText: '确定', cancelButtonText: '取消', type: 'warning',}
  ).then(() => {
      multipleSelection.value.forEach(item => {
        delrow(item)
    })
    ElMessage({type: 'success', message: '完成批量删除！',})
  }).catch(() => {
    ElMessage({type: 'info', message: '取消删除!',})
  })
}




</script>

<style scoped>
/*在ElementPlus中，可使用组件名称的类选择器选择对应组件，从而修改默认组件样式*/
.el-pagination {/*选择分页组件，默认采取Flex布局*/
  justify-content: center;/*水平方向居中对齐*/
  margin-top: 8px;
}


.topTool{
  display: flex;justify-content: space-between;margin-bottom: 8px;
}


</style>
