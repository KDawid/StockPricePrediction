import pandas as pd

class DataSetCreator:
    def __init__(self, csv_file_path, validation_set_split=500, used_days=5):
        self.FILE_PATH = csv_file_path
        self.VALIDATION_SET_SPLIT = validation_set_split
        self.USED_DAYS = used_days

    def getDataSets(self):
        data = pd.read_csv(self.FILE_PATH)

        keys = list(data.keys().get_values())
        data = data.values

        print('keys:' + str(keys))

        data_X, data_y = self.createLearningData(data, self.USED_DAYS, keys)

        training_set_X, training_set_y = data_X[:-self.VALIDATION_SET_SPLIT], data_y[:-self.VALIDATION_SET_SPLIT]
        validation_set_X, validation_set_y = data_X[-self.VALIDATION_SET_SPLIT:], data_y[-self.VALIDATION_SET_SPLIT:]

        return (training_set_X, training_set_y), (validation_set_X, validation_set_y)

    def createLearningData(self, data_set, used_days, keys):
        learning_data_X = []
        learning_data_y = []
        for i in range(used_days, len(data_set)):
            element = []
            for day in range(1, used_days):
                for j in [keys.index(key) for key in keys if key not in ["Date", "Open"]]:
                    element.append(data_set[i - day][j])
            learning_data_X.append(element)
            learning_data_y.append(data_set[i][keys.index("Close")])
        return learning_data_X, learning_data_y
