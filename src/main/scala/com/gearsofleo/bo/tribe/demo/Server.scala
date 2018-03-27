package com.gearsofleo.bo.tribe.demo

import se.chimps.bitzness.mini.jade.Jade4j
import se.chimps.cameltow.Cameltow
import se.chimps.cameltow.framework.handlers.{Action, Static}
import se.chimps.cameltow.framework.responsebuilders.Ok

import scala.concurrent.ExecutionContext
import scala.sys.ShutdownHookThread

object Server {
	implicit val ec = ExecutionContext.global

	def main(args:Array[String]):Unit = {
		val routes = Cameltow.routes()

		routes.GET("/", Action.sync(req => {
			Ok.html(render("index"))
		}))

		routes.GET("/widget1", Action.sync(req => {
			Thread.sleep(1000)
			Ok.html(render("widget", Map("image" -> "widget1", "sleep" -> "1000")))
		}))

		routes.GET("/widget2", Action.sync(req => {
			Thread.sleep(2000)
			Ok.html(render("widget", Map("image" -> "widget2", "sleep" -> "2000")))
		}))

		routes.GET("/widget3", Action.sync(req => {
			Thread.sleep(3000)
			Ok.html(render("widget", Map("image" -> "widget3", "sleep" -> "3000")))
		}))

		routes.GET("/jquery", Action.sync(req => {
			Ok.html(render("jquery"))
		}))

		routes.GET("/static/:file(.*)", Static.classpath("static"))

		val server = Cameltow.defaults()
	    	.handler(routes.handler)
	    	.listen()

		ShutdownHookThread(server.stop())

		server.start()
	}

	def render(template:String, model:Map[String, String] = Map()):Array[Byte] = Jade4j.classpath(s"templates/$template", model).render("utf-8")
}
