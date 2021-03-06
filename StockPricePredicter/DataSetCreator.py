from SentimentAnalyzer import SentimentAnalyzer
import numpy as np
import pandas as pd

class DataSetCreator:
    def __init__(self, csv_file_path, validation_set_split=500, used_days=5, use_sentiment=[]):
        self.FILE_PATH = csv_file_path
        self.VALIDATION_SET_SPLIT = validation_set_split
        self.USED_DAYS = used_days
        self.DATA = pd.read_csv(self.FILE_PATH)
        self.TITLE_SENTIMENTS = dict()
        self.SNIPPET_SENTIMENTS = dict()
        self.sentiment_analyzer = SentimentAnalyzer()
        self.use_sentiment = use_sentiment

    def getDataSets(self, type):
        data = self.DATA

        keys = list(data.keys().get_values())
        data = data.values

        print('keys:' + str(keys))

        if type == 'regr':
            data_X, data_y = self.__createRegressionLearningData(data, self.USED_DAYS, keys)
        elif type == 'high_class':
            data_X, data_y = self.__createHighClassificationLearningData(data, self.USED_DAYS, keys)
        elif type == 'close_class':
            data_X, data_y = self.__createCloseClassificationLearningData(data, self.USED_DAYS, keys)
        else:
            raise ValueError("type should be 'regr' or 'high_class' or 'close_class'")

        training_set_X, training_set_y = data_X[:-self.VALIDATION_SET_SPLIT], data_y[:-self.VALIDATION_SET_SPLIT]
        validation_set_X, validation_set_y = data_X[-self.VALIDATION_SET_SPLIT:], data_y[-self.VALIDATION_SET_SPLIT:]

        return (training_set_X, training_set_y), (validation_set_X, validation_set_y)

    def __createRegressionLearningData(self, data_set, used_days, keys):
        learning_data_X = []
        learning_data_y = []
        for i in range(used_days, len(data_set)):
            self.__addLearningElements(i, learning_data_X, data_set, used_days, keys)
            learning_data_y.append(data_set[i][keys.index("Close")])
        return learning_data_X, learning_data_y

    def __createHighClassificationLearningData(self, data_set, used_days, keys):
        goal = self.getMedianOfMaxes()
        learning_data_X = []
        learning_data_y = []
        for i in range(used_days, len(data_set)):
            self.__addLearningElements(i, learning_data_X, data_set, used_days, keys)
            learning_data_y.append(data_set[i][keys.index("High")] >= goal)
        return learning_data_X, learning_data_y

    def __createCloseClassificationLearningData(self, data_set, used_days, keys):
        learning_data_X = []
        learning_data_y = []
        for i in range(used_days, len(data_set)):
            self.__addLearningElements(i, learning_data_X, data_set, used_days, keys)
            learning_data_y.append(data_set[i][keys.index("Close")] > 1.0)
        return learning_data_X, learning_data_y

    def __addLearningElements(self, i, learning_data_X, data_set, used_days, keys):
        element = []
        for day in range(1, used_days):
            for j in [keys.index(key) for key in keys if key not in ["Date", "Open"]]:
                element.append(data_set[i - day][j])
                if 'title' in self.use_sentiment:
                    element.append(self.__getTitleSentimentData(data_set[i - day][keys.index("Date")]))
                if 'snippet' in self.use_sentiment:
                    element.append(self.__getSnippetSentimentData(data_set[i - day][keys.index("Date")]))
        learning_data_X.append(element)

    def getValidationValues(self, column):
        return self.DATA[column].values[-self.VALIDATION_SET_SPLIT:]

    def getMedianOfMaxes(self):
        return np.mean(self.DATA["High"][:-self.VALIDATION_SET_SPLIT].values)

    def __getTitleSentimentData(self, date):
        if date not in self.TITLE_SENTIMENTS.keys():
            int_date = [int(d) for d in date.split("/")]
            self.TITLE_SENTIMENTS[date] = self.sentiment_analyzer.getTitlesSentiment(int_date[2], int_date[0], int_date[1])
            print("Add title sentiments for day " + date + ":\t" + str(self.TITLE_SENTIMENTS[date]))
        return self.TITLE_SENTIMENTS[date]

    def __getSnippetSentimentData(self, date):
        if date not in self.SNIPPET_SENTIMENTS.keys():
            int_date = [int(d) for d in date.split("/")]
            self.SNIPPET_SENTIMENTS[date] = self.sentiment_analyzer.getSnippetsSentiment(int_date[2], int_date[0], int_date[1])
            print("Add snippet sentiments for day " + date + ":\t" + str(self.SNIPPET_SENTIMENTS[date]))
        return self.SNIPPET_SENTIMENTS[date]
