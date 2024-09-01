# # coding=utf-8
# #!/usr/bin/python

# # 接口请求示例为：http://open.api.tianyancha.com/services/v4/open/oneKey/c?keyword=中航重机股份有限公司

# # pip install requests
# import requests
# import time
# import hashlib
# import json

# #  token可以从 数据中心 -> 我的接口 中获取 aaa
# token = "aaa393877b8-8b20-4715-a245-35b76c0afebb"
# encode = 'utf-8'

# # 郑州信源信息技术股份有限公司
# url = "http://open.api.tianyancha.com/services/v4/open/oneKey/c?keyword=杭州银行股份有限公司"
# headers = {'Authorization': token}
# response = requests.get(url, headers=headers)

# # 结果打印
# print(response.status_code)
# print(response.text)


# coding=utf-8
#!/usr/bin/python

# 接口请求示例为：http://open.api.tianyancha.com/services/v3/open/investtree?flag=2&dir=down&keyword=北京百度网讯科技有限公司

# pip install requests
import requests
import time
import hashlib
import json

#  token可以从 数据中心 -> 我的接口 中获取
token = "aaa393877b8-8b20-4715-a245-35b76c0afebb"
encode = 'utf-8'

url = "http://open.api.tianyancha.com/services/v3/open/investtree?flag=4&dir=down&keyword=深圳市腾讯计算机系统有限公司"
headers = {'Authorization': token}
response = requests.get(url, headers=headers)

# 结果打印
print(response.status_code)
print(response.text)

# 将 response.text 存入文件
with open('response_output.txt', 'w', encoding='utf-8') as file:
    file.write(response.text)
