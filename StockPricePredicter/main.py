from DataSetCreator import DataSetCreator
from CloseClassificationLearning import CloseClassificationLearning
from HighClassificationLearning import HighClassificationLearning
from RegressionLearning import RegressionLearning
from Saver import ResultSaver

USED_DAYS = 5
VALIDATION_SET_SPLIT = 500
DAILY_PRICE = 100

#USE_SENTIMENT = []
USE_SENTIMENT = ['title', 'snippet']

CSV_FILE_PATH = "AAPL_normalized.csv"

#Create dataset maker
data_set_creator = DataSetCreator(csv_file_path=CSV_FILE_PATH, validation_set_split=VALIDATION_SET_SPLIT, used_days=USED_DAYS, use_sentiment=USE_SENTIMENT)

#create result saver
saver = ResultSaver()

#Regression
(training_set_X, training_set_y), (validation_set_X, validation_set_y) = data_set_creator.getDataSets(type='regr')
regr = RegressionLearning(training_set_X, training_set_y, validation_set_X, validation_set_y)
regr.run(daily_price=DAILY_PRICE)
saver.save(regr.results, 'regression.json')

#Classification for high values
(training_set_X, training_set_y), (validation_set_X, validation_set_y) = data_set_creator.getDataSets(type='high_class')
classification = HighClassificationLearning(training_set_X, training_set_y, validation_set_X, validation_set_y, data_set_creator.getMedianOfMaxes())
classification.run(daily_price=DAILY_PRICE, close_values=data_set_creator.getValidationValues("Close"))
saver.save(classification.results, 'high_classification.json')

#Classification for close values
(training_set_X, training_set_y), (validation_set_X, validation_set_y) = data_set_creator.getDataSets(type='close_class')
classification = CloseClassificationLearning(training_set_X, training_set_y, validation_set_X, validation_set_y)
classification.run(daily_price=DAILY_PRICE, close_values=data_set_creator.getValidationValues("Close"))
saver.save(classification.results, 'close_classification.json')

'''
sum=0
for i in range(len(training_set_y)):
    if training_set_y[i]:
        sum += 1
    if i % 100 == 99:
        print("%i. hundred: %i%% reached the goal value" % (i//100+1, sum))
        sum=0
'''
