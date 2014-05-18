package com.tkido.ticketgobble

object main extends App {
  import com.tkido.tools.Logger
  import scala.collection.mutable.{Map => MMap}
  
  Logger.level = Logger.INFO
  
  val countries = Parser("data/tickets.txt")
  val emap = countries.groupBy(_.end)
  Logger.debug(emap)
  
  val memo = MMap[Int, List[Country]]()
  def mc(day:Int) :List[Country] = {
    if(memo.contains(day)){
      Logger.debug("mc(%d) is cached".format(day))
      memo(day)
    }else{
      Logger.debug("mc(%d) is new".format(day))
      val rst =
        if(day < 0)
          List()
        else if(emap.contains(day))
          ( mc(day-1) :: emap(day).map(c => c :: mc(c.start - 1)) ).maxBy(_.size)
        else
          mc(day-1)
      memo(day) = rst
      Logger.debug("mc(%d) = %s".format(day, rst))
      rst
    }
  }
  val list = mc(365)
  Logger.debug(list)
  
  Logger.info("%d %s".format(list.size, list.map(_.name).sorted.mkString(" ")))
  
  Logger.close()
}
