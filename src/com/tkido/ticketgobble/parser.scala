package com.tkido.ticketgobble

object Parser {
  import com.tkido.tools.Logger
  import com.tkido.tools.Text
  
  def apply(path:String) :List[Country] = {
    val lines = Text.readLines(path)
    Logger.debug(lines)
    
    val re = """(.*?)\s(\d{1,2})/(\d{1,2})-(\d{1,2})/(\d{1,2})""".r
    def toCountry(source:String) :Option[Country] = {
      re.findFirstMatchIn(source) match {
        case Some(m) => Some(Country(m.group(1), Date.toInt(m.group(2), m.group(3)), Date.toInt(m.group(4), m.group(5))))
        case None    => None
      }
    }
    val countries = lines.map(toCountry).collect{case Some(c) => c}
    Logger.debug(countries)
    countries
  }

}