class Article:
    def __init__(self, row):
        if len(row) != 8:
            raise ValueError("Article data should contain 8 fields.")
        self.id = row[0]
        self.date = row[1]
        self.score = row[2]
        self.snippet = row[3]
        self.title = row[4]
        self.url = row[5]
        self.word_count = row[6]
        self.query_result_id = row[7]

    def __eq__(self, other):
        return self.id == other.id

    def __ne__(self, other):
        return not self.__eq__(other)
