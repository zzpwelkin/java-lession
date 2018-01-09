#-*-encoding:utf8
#!/user/bin/env jython
##
# Download dangdang books listed page of {url} and write items only to fine {fname}
# Command Example: jython scraper_demo2.py "http://search.dangdang.com/?key=%B4%FA%CA%FD%D3%A6%B8%C3%D5%E2%D1%F9%D1%A7&act=input" a.txt
#
# Note: 1. 因为python语言中没有java语言中关于synched关键字相关方面的用法， 为了保持写时候数据一致性，用的Queue
#       2. 这样做文件输出流没有关闭;
#       3. 分析多线程爬取而保存到一个文件中的利和弊
##

import re
from java.lang import String, Thread
from java.io import *
from java.net import URL, HttpURLConnection
from java.lang import Runnable, Thread
from java.util.concurrent import Executors, LinkedBlockingQueue

class ScraperAtom(Runnable):
    __writerQueue = None
    __url = None

    def __init__(self, writerQueue, url):
        self.__writerQueue = writerQueue
        self.__url = url

    def run(self):
        print(String.format("Current Thread is %d: %s.\r\n",Thread.currentThread().getId(), Thread.currentThread().getName()))
        print("    ---Get writer object from BlockingQueue...")
        writer = self.__writerQueue.take()
        url = URL(self.__url)
        con = url.openConnection()
        reader = BufferedReader(InputStreamReader(con.getInputStream()))

        # write source information
        def infLine(key, value):
            line = String.format("%s: %s", key, value)
            writer.write(line, 0, len(line))
            writer.newLine()

        infLine("URL", url.toString())
        infLine("Connection Status", con.getHeaderField(0))
        infLine("ContentEncoding", con.getContentEncoding())
        infLine("Content-Type", con.getContentType())

        content = ""
        lineContent = ""
        while True:
            lineContent = reader.readLine()
            if lineContent != None:
                if re.match("\s+\<li\s+ddt-pit=\"[\d]+\"\s+class=\"line[\d]+\"\s+id=\"p[\d]+\">", lineContent):
                    print(lineContent)
                    c = reader.readLine()
                    writer.write(c, 0, len(c))
                    writer.newLine()
            else:
                break

        print("    --- Put writer object to BlockingQueue.")
        self.__writerQueue.put(writer)
        reader.close()

def execute(urls, fname, poolsize=4):
    wq = LinkedBlockingQueue()
    wq.put(BufferedWriter(FileWriter(fname)))
    pool = Executors.newFixedThreadPool(poolsize)
    for u in  urls:
        pool.execute(ScraperAtom(wq, u))

    # close writer stream
    #w = wq.take()
    #w.close()
        

if __name__ == "__main__":
    import sys
    if len(sys.argv) < 3:
        print("Usage: cmd [url1] [url2] ... [file]")
    else:
        execute(sys.argv[1:-1], sys.argv[-1])

