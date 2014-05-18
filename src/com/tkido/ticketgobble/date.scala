package com.tkido.ticketgobble

object Date {
  val lastDays = List(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  def toInt(monthStr:String, dayStr:String) :Int = {
    val month = monthStr.toInt - 1
    val day = dayStr.toInt - 1
    lastDays.take(month).sum + day
  }
}