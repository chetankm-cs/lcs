object P721 {
    import collection.{mutable => m}
    @annotation.nowarn
    def accountsMerge(accounts: List[List[String]]): List[List[String]] = {
        val emailToName = accounts.flatMap {
            account => 
                val name = account.head
                account.tail.map { email => (email, name) } 
        }.toMap

        val graph = accounts.flatMap { account => 
            val firstEmail = account(1)
             account.tail.flatMap { email => 
                List(email -> firstEmail, firstEmail -> email)
            }
        }.groupMap(_._1)(_._2).mapValues(_.toList)

        var seen = Set[String]()
        val ans = m.ListBuffer[List[String]]()
        
         graph.keys.foreach { email => 
            if (!seen.contains(email)) {
                seen += email
                val stack = m.Stack[String]()
                stack.push(email)

                val component = m.ListBuffer[String]()

                while (stack.nonEmpty) {
                    val node = stack.pop()
                    component += node

                     graph(node).foreach { nei => 
                     if (!seen.contains(nei)) {
                        seen += nei
                        stack += nei
                     }
                    }
                }
                ans += (emailToName(email) :: component.result.sorted)
            }
        }
        ans.result
    }

}