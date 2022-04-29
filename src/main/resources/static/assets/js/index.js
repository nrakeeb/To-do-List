//Address to api
const ADDR = "http://localhost:8080";

//calling function
getAllTodoList()

// GET ALL TODO
function getAllTodoList() {
    return axios.get(`${ADDR}/Notes/getAll`)
        .then(
            function (res) {

                document.getElementById("taskList").innerHTML = "";

                res.data.forEach(makeTodo);
            }
        )
        .catch((err) => console.error(err))
}