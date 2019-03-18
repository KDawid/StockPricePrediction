from CloseClassificationLearning import CloseClassificationLearning
from DataSetCreator import DataSetCreator
from HighClassificationLearning import HighClassificationLearning
from RegressionLearning import RegressionLearning

USED_DAYS = 5
VALIDATION_SET_SPLIT = 500
DAILY_PRICE = 100
CSV_FILE_PATH = "AAPL_normalized.csv"

#Create dataset maker
data_set_creator = DataSetCreator(csv_file_path=CSV_FILE_PATH, validation_set_split=VALIDATION_SET_SPLIT, used_days=USED_DAYS)

#Regression
(training_set_X, training_set_y), (validation_set_X, validation_set_y) = data_set_creator.getDataSets(type='regr')
regr = RegressionLearning(training_set_X, training_set_y, validation_set_X, validation_set_y)
regr.run(daily_price=DAILY_PRICE)

#Classification for high values
(training_set_X, training_set_y), (validation_set_X, validation_set_y) = data_set_creator.getDataSets(type='high_class')
classification = HighClassificationLearning(training_set_X, training_set_y, validation_set_X, validation_set_y, data_set_creator.getMedianOfMaxes())
classification.run(daily_price=DAILY_PRICE, close_values=data_set_creator.getValidationValues("Close"))

#Classification for close values
(training_set_X, training_set_y), (validation_set_X, validation_set_y) = data_set_creator.getDataSets(type='close_class')
classification = CloseClassificationLearning(training_set_X, training_set_y, validation_set_X, validation_set_y)
classification.run(daily_price=DAILY_PRICE, close_values=data_set_creator.getValidationValues("Close"))

'''
sum=0
for i in range(len(training_set_y)):
    if training_set_y[i]:
        sum += 1
    if i % 100 == 99:
        print("%i. hundred: %i%% reached the goal value" % (i//100+1, sum))
        sum=0
'''