package sandbox.cases.cap8

import cats.{Applicative, Id}

import scala.concurrent.Future

//import cats.instances.future._ // for Applicative
import cats.instances.list._ // for Traverse
import cats.syntax.traverse._ // for traverse
import cats.syntax.functor._
//import scala.concurrent.ExecutionContext.Implicits.global


object Chapter8 extends App {


  trait UptimeClient[F[_]] {
    def getUptime(hostname: String) : F[Int]
  }

  class RealClient(hosts: Map[String, Int]) extends UptimeClient[Future] {
    def getUptime(hostname: String): Future[Int] =
      Future.successful(hosts.getOrElse(hostname, 0))
  }

  class TestClient(hosts: Map[String, Int]) extends UptimeClient[Id]  {
    def getUptime(hostname: String): Id[Int] = hosts.getOrElse(hostname, 0)
  }


  class UptimeService[F[_] : Applicative](client: UptimeClient[F]) {
    def getTotalUptime(hostnames: List[String]) : F[Int]=
      hostnames.traverse(client.getUptime).map(_.sum)
  }

  def testTotalUptime() = {
    val hosts = Map("host1" -> 10, "host2" -> 6)
    val client = new TestClient(hosts)
    val service = new UptimeService(client)
    val actual = service.getTotalUptime(hosts.keys.toList)
    val expected = hosts.values.sum
    assert(actual == expected)
    actual
  }


  // run
  println(testTotalUptime())

}
