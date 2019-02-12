import datetime
import sys


def gen_calendar(month, year):
    i = datetime.datetime(year, month, 1)

    while i.month == month:
        if i.weekday() != 6:
            entrada = "08:10"
            saida = "17:00"
            intervalo_inicio = "14:15"
            intervalo_fim = "15:15"
            if i.weekday() == 5:
                entrada = "08:00"
                saida = "12:40"
            print("%5s,%9s,      ,%11s,       ,%11s,       ,%7s,       ,       ,               " % (
                i.day, entrada, intervalo_inicio, intervalo_fim, saida
            ))
        i = i + datetime.timedelta(days=1)


gen_calendar(int(sys.argv[1]),int(sys.argv[2]))