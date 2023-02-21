
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
const username = cookieArr[2]
console.log(userId)
//DOM Elements
const submitForm = document.getElementById("review-form")
const reviewContainer = document.getElementById("review-container")

//Modal Elements
let reviewBody = document.getElementById("review-body")
let updateReviewBtn = document.getElementById('update-review-button')
let userName1 = "http://localhost:8080/reviews/1/user/username/"
const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/reviews/"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        body: document.getElementById("review-input").value
    }
    await addReview(bodyObj);
    document.getElementById("review-input").value = ''
}

async function addReview(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getReviews(userId);
    }
}

async function getReviews(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createReviewCards(data))
        .catch(err => console.error(err))
}

async function getReviewById(reviewId){
    await fetch(baseUrl + reviewId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleReviewEdit(reviewId){
    let bodyObj = {
        id: reviewId,
        body: document.getElementById("review-body").value
    }

    await fetch(baseUrl + reviewId, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getReviews(userId);
}
async function handleDelete(reviewId){
    await fetch(baseUrl + reviewId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getReviews(userId);
}

const createReviewCards = (array) => {
    reviewContainer.innerHTML = ''
    array.forEach(obj => {
        let reviewCard = document.createElement("div")
        reviewCard.classList.add("m-2")
        reviewCard.innerHTML = `
            <div class="card d-flex" style="width: 36rem; height: 10rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">

                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>

                    </div>
                </div>
            </div>
        `
        reviewContainer.append(reviewCard);
    })
}
function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{
    reviewBody.innerText = ''
    reviewBody.innerText = obj.body
    updateReviewBtn.setAttribute('data-review-id', obj.id)
}

getReviews(userId);

submitForm.addEventListener("submit", handleSubmit)

updateReviewBtn.addEventListener("click", (e)=>{
    let reviewId = e.target.getAttribute('data-review-id')
    handleReviewEdit(reviewId);
});