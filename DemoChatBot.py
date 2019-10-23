#import libraries
from chatterbot import ChatBot
from chatterbot.trainers import ListTrainer
import os

#Create a chatbot
bot=ChatBot('Candice')
bot.set_trainer(ListTrainer)

#training on english dataset
for files in os.listdir('C:/Users/HaSaN-PC/Downloads/Compressed/chatterbot-corpus-1.2.0/chatterbot_corpus/data/english/'):
    data=open('C:/Users/HaSaN-PC/Downloads/Compressed/chatterbot-corpus-1.2.0/chatterbot_corpus/data/english/' + files, 'r').readlines()
    bot.train(data)

#chat feature
while True:
    message=input('\t\t\tYou:')
    if message.strip()!='Bye':
        reply=bot.get_response(message)
        print('Candice:',reply)
    if message.strip()=='Bye':
        print('Candice: Bye')
        break