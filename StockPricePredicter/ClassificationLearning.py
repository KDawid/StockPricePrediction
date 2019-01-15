import numpy as np
from sklearn.svm import LinearSVC

class ClassificationLearning:
    def __init__(self, training_set_X, training_set_y, validation_set_X, validation_set_y):
        self.models = [LinearSVC()]
        self.training_set_X = training_set_X
        self.training_set_y = training_set_y
        self.validation_set_X = validation_set_X
        self.validation_set_y = validation_set_y

    def run(self):
        self.calculateBaseline()
        for model in self.models:
            self.runClassification(model)

    def calculateBaseline(self):
        sum = []
        # TODO
        print("Baseline: %f" % np.mean(sum))

    def runClassification(self, model):
        print("------------------------------------------------------------------------------------")
        print("Using model for %s" % model)

        model.fit(self.training_set_X, self.training_set_y)

        stock_price_predictions = model.predict(self.validation_set_X)

        sum = []
        # TODO
        print("Predicted: %f" % np.mean(sum))

        self.calculateConfusions(self.validation_set_y, stock_price_predictions)

        for i in range(len(self.validation_set_y)):
            print("Predicted: " + str(stock_price_predictions[i]) + ", actual: " + str(self.validation_set_y[i]))

        print("\n")

    def calculateConfusions(self, validation_set_y, stock_price_predictions):
        #TODO
        None
