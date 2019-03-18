import json

class ResultSaver:

    def save(self, dictionary, file_name):
        with open(file_name, 'w') as f:
            f.write(json.dumps(dictionary, indent=4, sort_keys=True))
