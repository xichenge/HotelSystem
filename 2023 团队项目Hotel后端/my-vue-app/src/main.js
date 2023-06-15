import { createApp } from 'vue'
// import './style.css'
import App from './App.vue'
import router from './router/index.js'
import ElementPlus from 'element-plus'
import * as ELIcons from '@element-plus/icons-vue'
import 'element-plus/dist/index.css'
const app=createApp(App)


for (let iconName in ELIcons) {
    app.component(iconName, ELIcons[iconName])
  }
  

app.use(router)
app.use(ElementPlus)
app.mount('#app')