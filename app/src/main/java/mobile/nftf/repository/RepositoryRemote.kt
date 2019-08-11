package mobile.nftf.repository

import mobile.nftf.network.api.ApiTest

class RepositoryRemote(private val apiTest: ApiTest) {

    fun fetchTodos() = apiTest.fetchTodos()

    fun addTodos(content: String) = apiTest.addTodo(content)

    fun updateTodo(id: Int, content: String) = apiTest.updateTodo(id, content)

    fun updateTodo(id: Int, done: Boolean) = apiTest.updateTodo(id, done = done)

    fun deleteTodo(id: Int) = apiTest.deleteTodo(id)

    fun updateSeq(id: Int, seq: Int) = apiTest.updateSeq(id, seq)
}