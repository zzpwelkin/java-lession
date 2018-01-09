#-*-encoding:utf8
#!/user/bin/env jython
##
# Download dangdang books listed page of {url} and write items only to fine {fname}
# Command Example: jython scraper_demo2.py "http://search.dangdang.com/?key=%B4%FA%CA%FD%D3%A6%B8%C3%D5%E2%D1%F9%D1%A7&act=input" a.txt
##

import re
from java.lang import String
from java.io import *
from java.net import URL, HttpURLConnection


def scraper(url, fname):
    url = URL(url)
    con = url.openConnection()
    reader = BufferedReader(InputStreamReader(con.getInputStream()))
    writer = BufferedWriter(FileWriter(fname))

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

    writer.close()
    reader.close()

if __name__ == "__main__":
    import sys
    if len(sys.argv) != 3:
        print("Usage: cmd [url] [file]")

    scraper(sys.argv[1], sys.argv[2])

