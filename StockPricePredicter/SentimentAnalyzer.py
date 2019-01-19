from SqlConnector import SqlConnector

import numpy as np
import re
from textblob import TextBlob

REGEXP = "(@[A-Za-z0-9]+)|([^0-9A-Za-z \t]) | (\w+:\ / \ / \S+)"

class SentimentAnalyzer:
    def __init__(self):
        self.sql = SqlConnector()

    def getTitlesSentiment(self, year, month, day):
        articles_data = self.sql.getArticlesOfOneDay(year, month, day)
        result = np.mean([self.__getSentiment(data.title) for data in articles_data])
        return 0.0 if np.isnan(result) else result

    def __clean(self, data):
        return ' '.join(re.sub(REGEXP, " ", data).split())

    def __getSentiment(self, data):
        analysis = TextBlob(self.__clean(data))
        return analysis.sentiment.polarity
