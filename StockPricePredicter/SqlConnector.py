from Article import Article

from datetime import datetime
from pandas.tseries.offsets import BDay
import psycopg2

DBNAME = "stock_price_prediction"
USER = "stockPredicter"
PASSWORD = "stockPredicter"

class SqlConnector:
    def __init__(self):
        self.conn = psycopg2.connect("dbname=%s user=%s password=%s" % (DBNAME, USER, PASSWORD))

    def getArticlesOfOneDay(self, year, month, day):
        toDay = datetime(year, month, day, 8, 30)
        fromDay = toDay - BDay(1)

        cur = self.conn.cursor()
        cur.execute(
            """SELECT * from article WHERE date_of_publication BETWEEN '""" +
            str(fromDay) + """' AND '""" + str(toDay) + """'""")
        rows = cur.fetchall()
        result = []
        for row in set(rows):
            result.append(Article(row))
        return result
