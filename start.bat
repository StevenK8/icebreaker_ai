@start java -cp IB_1_o.jar games.icebreaker.IBDuel -p 4536 -g

@start java -jar .\ia.jar -p 4536 -s localhost -c games.icebreaker.MyChallenger

@start java -cp IB_1_o.jar iialib.games.contest.Client -p 4536 -s localhost -c games.icebreaker.IBAlphaBetaChallenger