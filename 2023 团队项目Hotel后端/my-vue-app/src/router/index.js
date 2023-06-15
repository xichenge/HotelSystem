//引入路由依赖
import {createRouter, createWebHashHistory} from 'vue-router'
//引入相关组件
import TwoDemo from '../components/TwoDemo.vue'
import ProList1 from '../components/ProList1.vue'
//路由配置
const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            //默认路径
            path: '/test2',
            //对应的组件
            component:TwoDemo
        },{
            path:'/',
            component:ProList1
        }

    ]
})
export default router;