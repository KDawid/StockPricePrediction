from DataSetCreator import DataSetCreator
from RegressionLearning import RegressionLearning

USED_DAYS = 10
VALIDATION_SET_SPLIT = 500
CSV_FILE_PATH = "AAPL_normalized.csv"

data_set_creator = DataSetCreator(csv_file_path=CSV_FILE_PATH, validation_set_split=VALIDATION_SET_SPLIT, used_days=USED_DAYS)
(training_set_X, training_set_y), (validation_set_X, validation_set_y) = data_set_creator.getDataSets()

regr = RegressionLearning(training_set_X, training_set_y, validation_set_X, validation_set_y)
regr.run()
