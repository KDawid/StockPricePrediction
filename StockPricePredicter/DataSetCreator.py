from SentimentAnalyzer import SentimentAnalyzer
import numpy as np
import pandas as pd

class DataSetCreator:
    def __init__(self, csv_file_path, validation_set_split=500, used_days=5):
        self.FILE_PATH = csv_file_path
        self.VALIDATION_SET_SPLIT = validation_set_split
        self.USED_DAYS = used_days
        self.DATA = pd.read_csv(self.FILE_PATH)
        self.SENTIMENTS = dict()
        self.sentiment_analyzer = SentimentAnalyzer()

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
                element.append(self.__getSentimentData(data_set[i - day][keys.index("Date")]))
        learning_data_X.append(element)

    def getValidationValues(self, column):
        return self.DATA[column].values[-self.VALIDATION_SET_SPLIT:]

    def getMedianOfMaxes(self):
        return np.mean(self.DATA["High"][:-self.VALIDATION_SET_SPLIT].values)

    def __getSentimentData(self, date):
        if date not in self.SENTIMENTS.keys():
            int_date = [int(d) for d in date.split("/")]
            self.SENTIMENTS[date] = self.sentiment_analyzer.getTitlesSentiment(int_date[2], int_date[0], int_date[1])
            print("Add sentiments for day " + date + ":\t" + str(self.SENTIMENTS[date]))
        return self.SENTIMENTS[date]
