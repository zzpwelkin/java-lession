#!/user/bin/env jython
##
# Download html page of {url} and write to file {fname}
##
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

    lineContent = ""
    while True:
        lineContent = reader.readLine()
        if lineContent != None:
            writer.write(lineContent, 0, len(lineContent))
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

