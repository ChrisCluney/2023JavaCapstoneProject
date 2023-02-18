
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("reservation-form")
const reservationContainer = document.getElementById("reservation-container")

//Modal Elements
let reservationBody = document.getElementById(`reservation-body`)
let reservationDate = document.getElementById("reservation-input-date-edit")
let reservationTime = document.getElementById("reservation-input-time-edit")
let updateReservationBtn = document.getElementById('update-reservation-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/reservations/"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        date: document.getElementById("reservation-input-date").value,
        time: document.getElementById("reservation-input-time").value
    }
    await addReservation(bodyObj);
    document.getElementById("reservation-input-date").value = '',
    document.getElementById("reservation-input-time").value = ''
}

async function addReservation(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getReservations(userId);
    }
}

async function getReservations(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createReservationCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(reservationId){
    await fetch(baseUrl + reservationId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getReservations(userId);
}

async function getReservationById(reservationId){
    await fetch(baseUrl + reservationId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleReservationEdit(reservationId){
    let bodyObj = {
        id: reservationId,
        date: reservationDate.value,
        time: reservationTime.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getReservations(userId);
}

const createReservationCards = (array) => {
    reservationContainer.innerHTML = ''
    array.forEach(obj => {
        let reservationCard = document.createElement("div")
        reservationCard.classList.add("m-2")
        reservationCard.innerHTML = `
            <div class="card d-flex" style="width: 36rem; height: 10rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">

                    <p class="card-text">You have a reservation on  ${obj.date} at ${obj.time}</p>



                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getReservationById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#reservation-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        reservationContainer.append(reservationCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{
    reservationDate.innerText = ''

    reservationDate.innerText = obj.date
    reservationTime.innerText = obj.time
    updateReservationBtn.setAttribute('data-reservation-id', obj.id)
}

getReservations(userId);

submitForm.addEventListener("submit", handleSubmit)

updateReservationBtn.addEventListener("click", (e)=>{
    let reservationId = e.target.getAttribute('data-reservation-id')
    handleReservationEdit(reservationId);
});