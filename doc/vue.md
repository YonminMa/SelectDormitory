# 安装Node
进入以下网址下载LTS版本的Node并安装
```
https://nodejs.org/en/
```

# 使用Vue CLI创建项目
## 通过命令行创建
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
Axios是一个基于[promise](https://javascript.info/promise-basics)的网络请求库，作用于`node.js`和浏览器中。
它是[isomorphic](https://www.lullabot.com/articles/what-is-an-isomorphic-application)的(即同一套代码可以运行在浏览器和node.js中)。
在服务端它使用原生node.js`http` 模块, 而在客户端 (浏览端) 则使用 XMLHttpRequests。

在项目下打开Terminal，使用以下命令进行安装Axios
```shell
npm install axios
```