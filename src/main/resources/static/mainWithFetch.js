//список функций в объект
 const userFetch = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=UTF-8',
        'Referer': null
    },
    getAllUsers: async () => await fetch('/api/users'),
    addUser: async (user) => await fetch('/api/users', {method: "POST", headers: userFetch.head, body: JSON.stringify(user)}),
    updateUser: async (user) => await fetch(`http://localhost:8080/api/users/`, {method: 'PUT', headers: userFetch.head, body: JSON.stringify(user)}),
    deleteUserByID: async (id) => await fetch(`http://localhost:8080/api/users/` + id, {method: 'DELETE', headers: userFetch.head})
    // getUserByUsername: async () => await fetch(`http://localhost:8080/api/name`),
    // getUserById: async (id) => await fetch(`http://localhost:8080/api/users/` + id),
}

//listener для кнопки добавления нового user
const buttonNewUser = document.querySelector('#buttonNewUser')
buttonNewUser.addEventListener('click', addNewUser, false)


// setTimeout(() => {
//     getUsers()
// }, 1500)

getUsers()

//получаем юзеров - заполняем таблицу
function getUsers() {
    userFetch.getAllUsers()
    .then(res => { res.json()
    .then(users => {
        users.forEach((user) => {
            console.log(user)
            let userRoles = getRoles(user.roles)
            let insert =
                `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${userRoles}</td>
                <td><button type="submit" onclick="editUser(${user.id})" class="btn btn-info" data-toggle="modal" data-target="#modal-container-editUser">Edit</button></td>
                <td><button type="submit" onclick="deleteUser(${user.id})" class="btn btn-danger" data-toggle="modal" data-target="#modal-container-deleteUser">Delete</button></td>
                </tr>`
            document.querySelector('#bodyWithUsers').insertAdjacentHTML('beforeend', insert)
        })
    })
    })
}

//добавление нового user
function addNewUser(env) {
    env.preventDefault()
    let name = document.getElementById('newUserName').value
    let surname = document.getElementById('newUserSurname').value
    let age = document.getElementById('newUserAge').value
    let email = document.getElementById('newUserEmail').value
    let password = document.getElementById('newUserPassword').value
    let elementWithRoles = document.getElementById('newUserRoles')
    let role = elementWithRoles.options[elementWithRoles.selectedIndex].value
    let roles = addRoles(role)
    let user = {
        name: name,
        surname: surname,
        age: age,
        email: email,
        password: password,
        roles: roles
    };
    console.log(user)
    userFetch.addUser(user).then(() => {
        document.getElementById('newUserName').value = ``;
        document.getElementById('newUserSurname').value = ``;
        document.getElementById('newUserAge').value = ``;
        document.getElementById('newUserEmail').value = ``;
        document.getElementById('newUserPassword').value = ``;
        document.getElementById('newUserRoles').value = ``;
        document.getElementById('bodyWithUsers').innerHTML = ``;
        getUsers();
    })
    console.log(user)
}

//роли user в строку
function getRoles(roles) {
    let result = ''
    for (let role of roles) {
        result += role.name.substring(5)
        result += ' '
    }
    result = result.substring(0, result.length - 1)
    return result
}

//роли нового user
function addRoles(role) {
    let roles = [];
    roles.push({id: 1, name: 'ROLE_USER', authority: 'ROLE_USER'});
    if (role === 'ADMIN') {
        roles.push({id: 2, name: 'ROLE_ADMIN', authority: 'ROLE_ADMIN'});
    }
    console.log(roles)
    return roles;
}



