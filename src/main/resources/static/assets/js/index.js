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

// CREATE TODO
function createTodo() {

    let data = document.getElementById("todo-text").value
    console.log(data, "hello")
    axios.post(ADDR + "/Notes/create", {
        comments: data
    }).then(function (res) {
        makeTodo(res.data)
    })
}

// UPDATE TODO
function updateTodo(id) {
    let updatedText = document.getElementById("input_" + id).value;
    axios.put(`${ADDR}/Notes/update/${id}`, {
        comments: updatedText
    }).catch(err => alert(err))
}



// TODO DOM ELEMENTS AND ATTRIBUTES
function makeTodo(todoItem) {
    const task = document.createElement("div");
    task.className = "task";
    task.id = todoItem.id;

    const taskContent = document.createElement("div");
    taskContent.classNames = "content";

    const inputTag = document.createElement("input");

    inputTag.id = "input_" + todoItem.id;
    inputTag.type = "text";
    inputTag.className = "text";
    inputTag.value = todoItem.comments;
    inputTag.readOnly = true;


    taskContent.appendChild(inputTag);
    task.appendChild(taskContent);

    const action = document.createElement("div");
    action.className = "actions";

    const updateButton = document.createElement("button");
    updateButton.innerText = "update";
    updateButton.id = "update_" + todoItem.id;
    updateButton.className = "update";
    updateButton.onclick = function () {
        toggleUpdate(todoItem.id);
    }

    const completeButton = document.createElement("button");
    completeButton.innerText = "complete";
    completeButton.id = "complete_" + todoItem.id;
    completeButton.className = "complete";
    completeButton.onclick = function () {
        deleteToDoList(todoItem.id);
    }

    action.appendChild(updateButton);
    action.appendChild(completeButton);

    task.appendChild(action);

    document.getElementById("taskList").appendChild(task)
}