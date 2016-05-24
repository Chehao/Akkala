import akka.actor.Actor
import akka.event.Logging
import akka.actor.UntypedActor
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.ActorRef

object GameMarket extends App {

  val system = ActorSystem("GameMarket")
  val peter = system.actorOf(Props(classOf[GameAccountActor], "Peter", 1000, 0), "user1")
  val processor = system.actorOf(Props(classOf[GameTransactionProcessActor]), "processor")
  for (a <- 1 to 10) {    
    processor.tell(TransactionMessage(peter, BuyMessage(100, 2)), processor)    
    if(a > 8){
      processor.tell(TransactionMessage(peter, DepositMessage(50)), processor)
    }
  }
  system.terminate()
}

case class TransactionMessage(account: ActorRef, actionMsg: Message)

abstract class Message

case class BuyMessage(price: Int, quantity: Int) extends Message

case class DepositMessage(amount: Int) extends Message

case class ResultMessage(owner: String, balance: Int, quantity: Int) extends Message

class GameAccountActor(val owner: String, var balance: Int, var quantity: Int) extends UntypedActor {
  val log = Logging(context.system, this)

  def onReceive(message: Any): Unit = {
    message match {
      case msg: BuyMessage => {
        log.info("withdraw " + msg.price * msg.quantity)
        if (balance >= msg.price * msg.quantity) {
          balance = balance - msg.price * msg.quantity
          quantity += msg.quantity
        }
        val b = balance
        val q = quantity
        sender().tell(ResultMessage(owner, b, q), self)
      }
      case msg: DepositMessage => {
        log.info("Deposit: " + msg.amount)
        balance += msg.amount
        val b = balance
        val q = quantity
        sender().tell(ResultMessage(owner, b, q), self)
      }
      case _ => log.info("received unknown message")
    }
  }
}

class GameTransactionProcessActor() extends UntypedActor {
  val log = Logging(context.system, this)

  def onReceive(message: Any): Unit = {
    message match {
      case transcation: TransactionMessage => {
        log.info("action " + transcation.actionMsg)
        transcation.account.tell(transcation.actionMsg, self)
      }
      case msg: ResultMessage => log.info(msg.owner + ": balance:" + msg.balance + ", items " + msg.quantity)
      case _ => log.info("received unknown message")
    }
  }
}

 