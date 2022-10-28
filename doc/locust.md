# 安装Locust
1. 安装Python3.6版本并将其添加到环境变量
2. 安装PyCharm使用上面的Python3.6作为解释器
3. pip使用清华源执行以下命令
    ```shell
    pip install locust
    locust --v
    ```
    显示`locust 2.8.6`安装成功
# 编写性能测试用例
编写`locustfile.py`文件
```python
# locustfile.py

from locust import HttpUser, between, task
import os


class MyUser(HttpUser):
    wait_time = between(1, 2)

    @task
    def index_page(self):
        self.client.get("http://localhost:8088/")
```
# 启动Locust执行压测
## 通过WebUI执行
在命令行窗口输入
```shell
locust -f locustfile.py
```
访问WebUI链接
```
http://localhost:8089/
```

输入参数：
- Number of users (peak concurrency)：总模拟用户的数量
- Spawn rate (users started/second)：每秒生成的用户数量
- Host (e.g. http://www.example.com)：访问的网址（虽然locustfile.py中设置访问了网址但是不能为空，可以随便填写）
## 通过命令行执行
```shell
locust -f locustfile.py --headless -u 100 -r 10 -H xx -t 1h30m
```
其中
- --headless表示非GUI模式
- -u为Number of users参数
- -r为Spawn rate参数
- -H为Host参数
- -t为测试时间

# 测试/order接口
```python
class MyUser(HttpUser):
    wait_time = between(1, 2)

    @task
    def index_page(self):
        payload = {"uid": "123456", "building": "5"}
        self.client.post("http://localhost:8088/order", data=payload)


if __name__ == '__main__':
    os.system("locust -f locustfile.py")
```