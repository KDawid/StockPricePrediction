from DataSetCreator import DataSetCreator

import numpy as np
from sklearn.ensemble import AdaBoostClassifier, GradientBoostingClassifier, RandomForestClassifier
from sklearn.gaussian_process import GaussianProcessClassifier
from sklearn.linear_model import SGDClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.neural_network import MLPClassifier
from sklearn.svm import LinearSVC
from sklearn.tree import DecisionTreeClassifier

class HighClassificationLearning:
    def __init__(self, training_set_X, training_set_y, validation_set_X, validation_set_y, goal):
        self.models = [LinearSVC(), SGDClassifier(), KNeighborsClassifier(),
                       DecisionTreeClassifier(), GaussianProcessClassifier(),
                       GradientBoostingClassifier(n_estimators=100),
                       RandomForestClassifier(), MLPClassifier(), AdaBoostClassifier()]
        self.training_set_X = training_set_X
        self.training_set_y = training_set_y
        self.validation_set_X = validation_set_X
        self.validation_set_y = validation_set_y
        self.goal = goal
        self.results = dict()
        self.results['__info__'] = dict()

    def run(self, daily_price=1000, close_values=None):
        self.__printStartPattern()
        print("Goal value: %f" % self.goal)
        self.__calculateBaseline(daily_price, close_values)
        self.__calculateBestAndWorstScenario(daily_price, close_values)
        for model in self.models:
            self.__runClassification(model, daily_price, close_values)
        self.__printEndPattern()

    def __calculateBaseline(self, daily_price=1000, close_values=None):
        sum = []
        for i in range(len(self.validation_set_y)):
            if self.validation_set_y[i]:
                sum.append(daily_price*self.goal)
            else:
                sum.append(daily_price * close_values[i])
        baseline = np.sum(sum)
        print("Baseline: %f$" % baseline) # If I buy every day
        self.baseline = baseline
        self.results['__info__']['baseline'] = baseline
        self.results['__info__']['daily_price'] = daily_price

    def __calculateBestAndWorstScenario(self, daily_price=1000, close_values=None):
        best_sum = []
        worst_sum = []
        for i in range(len(self.validation_set_y)):
            if self.validation_set_y[i]:
                best_sum.append(daily_price * self.goal)
                worst_sum.append(daily_price)
            else:
                worst_sum.append(daily_price * close_values[i])
                best_sum.append(daily_price)
        print("Theoretical maximum price: %f$" % np.sum(best_sum))
        print("Theoretical minimum price: %f$" % np.sum(worst_sum))
        self.results['__info__']['theoretical_max'] = np.sum(best_sum)
        self.results['__info__']['theoretical_min'] = np.sum(worst_sum)

    def __runClassification(self, model, daily_price, close_values):
        print("------------------------------------------------------------------------------------")
        model_str = str(model).split("(")[0]
        print("Using model for %s" % model_str)

        model.fit(self.training_set_X, self.training_set_y)

        stock_price_predictions = model.predict(self.validation_set_X)

        sum = []
        for i in range(len(stock_price_predictions)):
            if stock_price_predictions[i]:
                if self.validation_set_y[i]:
                    sum.append(daily_price*self.goal)
                else:
                    sum.append(daily_price * close_values[i])
            else:
                sum.append(daily_price)
        result = np.sum(sum)
        print("Predicted: %f$" % result)
        self.results[model_str] = float(result - len(self.validation_set_y)*daily_price)

        self.__calculateConfusions(self.validation_set_y, stock_price_predictions)
        print("\n")

    def __calculateConfusions(self, validation_set_y, stock_price_predictions):
        true_positive = 0
        false_positive = 0
        true_negative = 0
        false_negative = 0

        for i in range(len(validation_set_y)):
            if stock_price_predictions[i]:
                if validation_set_y[i]:
                    true_positive += 1
                else:
                    false_positive += 1
            else:
                if validation_set_y[i]:
                    false_negative += 1
                else:
                    true_negative += 1
        print("\tTP: %i\tFP: %i\n\tFN: %i\tTN: %i" % (true_positive, false_positive, false_negative, true_negative))

    def __printStartPattern(self):
        print("\n\t##################################")
        print("\t# STARTING 'HIGH' CLASSIFICATION #")
        print("\t##################################\n")

    def __printEndPattern(self):
        print("\n\t######################################")
        print("\t# 'HIGH' CLASSIFICATION HAS FINISHED #")
        print("\t######################################\n")
