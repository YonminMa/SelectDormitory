# 安装Node
进入以下网址下载LTS版本的Node并安装
```
https://nodejs.org/en/
```

# 使用Vue CLI创建项目
Vue 提供了一个官方的 CLI，为单页面应用 (SPA) 快速搭建繁杂的脚手架。
它为现代前端工作流提供了 batteries-included 的构建设置。
只需要几分钟的时间就可以运行起来并带有热重载、保存时 lint 校验，以及生产环境可用的构建版本。
##  通过命令行创建
使用以下命令安装Vue CLI并创建一个新项目
```shell
npm install -g @vue/cli
vue create hello-world
```
根据自己的需求做出相应的配置选择

## 通过WebStorm创建
选择创建Vue项目，WebStorm自动找到已经安装好的npm，更改项目名称后点击Create

# 安装Element Plus
Element Plus是基于Vue3的组件库
在项目下打开Terminal，使用以下命令进行安装Element Plus
```shell
npm install element-plus --save
```
# 安装Axios
Axios 是一个基于 [promise](https://javascript.info/promise-basics) 的网络请求库，作用于`node.js`和浏览器中。
它是 [isomorphic](https://www.lullabot.com/articles/what-is-an-isomorphic-application) 的(即同一套代码可以运行在浏览器和 node.js 中)。
在服务端它使用原生 node.js `http` 模块, 而在客户端 (浏览端) 则使用 XMLHttpRequests。

在项目下打开 Terminal，使用以下命令进行安装Axios
```shell
npm install axios
```
 
# 前端路由VueRouter
Vue Router 是 Vue.js 的官方路由。
它与 Vue.js 核心深度集成，让用 Vue.js 构建单页应用变得轻而易举。
## 安装vue-router
在项目下打开 Terminal，使用以下命令进行安装Vue Router
```shell
npm install vue-router@4
```
注意：Vue Router4搭配Vue3使用，Vue Router3搭配Vue2使用
## vue-router基本使用
在`src`目录下新建`router`文件夹，在`router`文件夹中新建`index.js`文件，以下未明确提到在`main.js`中的代码都在`index.js`中
### 引入vue-router（和模块）
```js
import { createRouter, createWebHashHistory } from 'vue-router';
import Discover from "@/components/MyDiscover";
import Friends from "@/components/MyFriends";
import My from "@/components/MyMusic";
```
### 定义路由
```js
// 定义路由
const routes = [
    // 使用redirect将/重定向到/discover
    { path: '/', redirect: '/discover'},
    { path: '/discover', component: Discover },
    { path: '/friends', component: Friends },
    { path: '/music', component: Music }
]
```
### 创建路由
```js
// 创建一个名为myRouter的路由
export const myRouter = createRouter({
    history: createWebHashHistory(),
    routes
})
```
### 将路由加入到实例
此部分代码在`main.js`中
```js
// main.js
import { createApp } from 'vue'
import App from './App.vue'
// 不可以直接使用import myRouter from '@/router/index.js'
import {myRouter}  from '@/router/index.js'

const app = createApp(App)
app.use(myRouter)
app.mount('#app')
```
### 在App.vue中使用
```html
<template>
  <router-link to="/discover">发现音乐</router-link>
  <router-link to="/friends">关注</router-link>
  <router-link to="/music">我的音乐</router-link>
  <!-- 标签占位符 -->
  <router-view></router-view>
</template>
```

## 嵌套路由
### 创建路由部分代码
```js
const routes = [
    // 使用redirect将/重定向到/discover
    { path: '/', redirect: '/discover'},
    {
        path: '/discover',
        component: Discover,
        // children表示嵌套路由
        children: [
            // path参数前不可以加/
            { path: 'toplist', component: TopList },
            { path: 'playlist', component: PlayList }
        ]
    },
    { path: '/friends', component: Friends },
    { path: '/music', component: Music }
]
```
### 在MyDiscover.vue中使用
```js
<template>
  <h1>发现</h1>
  <router-link to="/discover/toplist">热榜</router-link>
  <router-link to="/discover/playlist">歌单</router-link>
  <hr></hr>
  <router-view></router-view>
</template>
```
## 动态路由
### 使用路由参数
在路由中通过在参数前添加`:`的方式使用动态参数，获取到链接后将参数通过`{{$router.params.xx}}`的形式进行传递
代码实例如下
```js
// 定义路由
const routes = [
    // 动态路由
    {
        path: '/music',
        component: MyMusic,
        children: [
            { path: ':id', component: Music}
        ]
    }
]
```
在Music.vue中获取router的参数
```html
<template>
  <h3>点击音乐{{$route.params.id}}按钮</h3>
</template>
```
MyMusic.vue中的代码
```html
<template>
  <h1>我的音乐</h1>
  <router-link to="/music/1">音乐1</router-link>
  <router-link to="/music/2">音乐2</router-link>
  <router-link to="/music/3">音乐3</router-link>
  <hr>
  <router-view></router-view>
</template>
```
此时访问`/music`链接，进入MyMusic组件。
点击音乐1按钮，跳转到`/music/1`链接，进入Music组件，此时参数`id`被赋值为`1`。
在Music组件中通过`{{$route.params.id}}`获取到`id`的值`1`，因此显示`点击音乐1按钮`。

### 使用Props传参
设置跳转到Music组件路由的prop参数为true
```js
{ path: ':id', component: Music, props: true }
```
在Music组件中添加`id`参数，并直接通过`{{id}}`调用
```js
<template>
    <h3>点击音乐{{id}}按钮</h3>
</template>

<script>
export default {
    name: "MyProduct",
    props: ["id"]
}
</script>
```
## 编程式导航
在组件中使用自定义方法传递参数
```js
<template>
  <h1>我的音乐</h1>
  <button @click="gotoMusic(1)">商品1</button>
  <button @click="gotoMusic(2)">商品2</button>
  <button @click="gotoMusic(3)">商品3</button>
  <hr>
  <!-- 把router-link链接的组件显示在router-view中 -->
  <router-view></router-view>
</template>

<script>
export default {
  name: "myMusic",
  methods: {
    gotoMusic (id) {
      this.$router.push('/music/' + id)
    }
  }
}
</script>
```