from chatterbot import ChatBot
from chatterbot.trainers import ChatterBotCorpusTrainer
import json
from django.views.generic.base import TemplateView
from django.views.generic import View
from django.http import JsonResponse
from chatterbot import ChatBot
from chatterbot.response_selection import *

chatbot = ChatBot('Chatterbot',
trainer='chatterbot.trainers.CorpusTrainer', 
storage_adapter='chatterbot.storage.SQLStorageAdapter',
database_uri='sqlite:///database.db',
response_selection_method=get_first_response,
logic_adapters=[
        {
            # "import_path": "chatterbot.logic.RouteLogic",
            "import_path": "chatterbot.logic.BestMatch",
            "import_path": "chatterbot.logic.MathematicalEvaluation",
            "statement_comparison_function": "chatterbot.comparisons.levenshtein_distance",
            "response_selection_method": "chatterbot.response_selection.get_first_response",

            # "import_path": "sample.LogicAdapter"
        }
    ]
)

trainer = ChatterBotCorpusTrainer(chatbot)
trainer.train(
    # "chatterbot.corpus.english.greetings",
    # "chatterbot.corpus.english.ai",
    # "chatterbot.corpus.english.computers",
    # "chatterbot.corpus.english.conversations",
    # "chatterbot.corpus.english.emotion",
    # "chatterbot.corpus.english.food",
    # "chatterbot.corpus.english.gossip",
    # "chatterbot.corpus.english.history",
    # "chatterbot.corpus.english.humor",
    # "chatterbot.corpus.english.health",
    # "chatterbot.corpus.bangla.botprofile",
    # "chatterbot.corpus.bangla.computer",
    # "chatterbot.corpus.bangla.emotions",
    # "chatterbot.corpus.bangla.greetings",
    # "chatterbot.corpus.buses.route3",
    # "chatterbot.corpus.buses.basic_answer",
    # # "chatterbot.corpus.buses.broute",
    # "chatterbot.corpus.english.conversations",

    # "chatterbot.corpus.route3", 
    # "chatterbot.corpus.banglaroute",
    "chatterbot.corpus.buses.route3",
    "chatterbot.corpus.buses.basic",
    "chatterbot.corpus.english.greetings",
    "chatterbot.corpus.english.conversations",
    "chatterbot.corpus.english.ai",
    "chatterbot.corpus.english.botprofile",
    "chatterbot.corpus.english.computers",
    "chatterbot.corpus.english.emotion",
    "chatterbot.corpus.english.food",
    "chatterbot.corpus.english.gossip",
    "chatterbot.corpus.english.health",
    "chatterbot.corpus.english.history",
    "chatterbot.corpus.english.humor",
    "chatterbot.corpus.english.literature",
    "chatterbot.corpus.english.money",
    "chatterbot.corpus.english.movies",
    "chatterbot.corpus.english.politics",
    "chatterbot.corpus.english.psychology",
    "chatterbot.corpus.english.science",
    "chatterbot.corpus.english.sports",
    "chatterbot.corpus.english.trivia",
    "chatterbot.corpus.bangla.botprofile",
    "chatterbot.corpus.bangla.greetings",
    "chatterbot.corpus.bangla.emotions",
    "chatterbot.corpus.bangla.computer",
    # "chatterbot.corpus.thai.greeting",
    # "chatterbot.corpus.telugu.conversations",
    # "chatterbot.corpus.tchinese.ai",
    # "chatterbot.corpus.tchinese.botprofile",
    # "chatterbot.corpus.tchinese.conversations",
    # "chatterbot.corpus.tchinese.emotion",
    # "chatterbot.corpus.tchinese.food",
    # "chatterbot.corpus.tchinese.gossip",
    # "chatterbot.corpus.tchinese.greetings",
    # "chatterbot.corpus.tchinese.history",
    # "chatterbot.corpus.tchinese.humor",
    # "chatterbot.corpus.tchinese.literature",
    # "chatterbot.corpus.tchinese.money",
    # "chatterbot.corpus.tchinese.movies",
    # "chatterbot.corpus.tchinese.politics",
    # "chatterbot.corpus.tchinese.psychology",
    # "chatterbot.corpus.tchinese.science",
    # "chatterbot.corpus.tchinese.sports",
    # "chatterbot.corpus.tchinese.trivia",
    # "chatterbot.corpus.swedish.ai",
    # "chatterbot.corpus.swedish.conversations",
    # "chatterbot.corpus.swedish.food",
    # "chatterbot.corpus.swedish.greetings",
    # "chatterbot.corpus.swedish.sports",
    # "chatterbot.corpus.spanish.conversations",
    # "chatterbot.corpus.spanish.greetings",
    # "chatterbot.corpus.spanish.trivia",
    # "chatterbot.corpus.russian.conversations",
    # "chatterbot.corpus.portuguese.compliment",
    # "chatterbot.corpus.portuguese.conversations",
    # "chatterbot.corpus.portuguese.greetings",
    # "chatterbot.corpus.portuguese.linguistic_knowledge",
    # "chatterbot.corpus.portuguese.proverbs",
    # "chatterbot.corpus.portuguese.suggestions",
    # "chatterbot.corpus.portuguese.trivia",
    # "chatterbot.corpus.portuguese.unilab",
    # "chatterbot.corpus.odia.conversations",
    # "chatterbot.corpus.odia.greetings",
    # "chatterbot.corpus.marathi.conversations",
    # "chatterbot.corpus.marathi.greetings",
    # "chatterbot.corpus.italian.conversations",
    # "chatterbot.corpus.italian.greetings",
    # "chatterbot.corpus.italian.trivia",
    # "chatterbot.corpus.indonesia.conversations",
    # "chatterbot.corpus.indonesia.greetings",
    # "chatterbot.corpus.indonesia.trivia",
    # "chatterbot.corpus.hindi.coversations",
    # "chatterbot.corpus.hindi.greetings",
    # "chatterbot.corpus.hebrew.botprofile",
    # "chatterbot.corpus.hebrew.conversations",
    # "chatterbot.corpus.hebrew.greetings",
    # "chatterbot.corpus.german.greetings",
    # "chatterbot.corpus.german.conversations",
    # "chatterbot.corpus.french.botprofile",
    # "chatterbot.corpus.french.conversations",
    # "chatterbot.corpus.french.food",
    # "chatterbot.corpus.french.greetings",
    # "chatterbot.corpus.french.trivia",
    "chatterbot.corpus.custom.myown",
)