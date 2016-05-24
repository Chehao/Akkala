import akka.actor.Actor
import akka.event.Logging
import akka.actor.UntypedActor
import akka.actor.ActorSystem
import akka.actor.Props

object GameMarket extends App{
  
  val system = ActorSystem("GameMarket")
  val gamer = system.actorOf(Props(classOf[GameAccountActor], 1000), "gamer1")
  gamer.tell(WithdrawMessage(100), gamer)
  
  system.terminate()
}

case class WithdrawMessage(amount:Int)

case class DepositMessage(amount:Int)

class GameAccountActor(balance:Int) extends UntypedActor {
  val log = Logging(context.system, this)

  def onReceive(message: Any): Unit = {
    message match {
      case msg: WithdrawMessage => log.info("withdraw " + msg.amount)
      case _ => log.info("received unknown message")
    }
  }
}