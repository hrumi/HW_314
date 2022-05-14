
//const h2r = document.getElementsByTagName('h2')
//const h2r = document.getElementsByClassName('h2-class')[0]
//const h2r = document.querySelector('.h2-class')

const h1r = document.getElementById('Hello')
const h2r = h1r.nextElementSibling

const h2List = document.querySelectorAll('h2')
const h3r = h2List[h2List.length - 1]


setTimeout(() => {
    addStyleTo(h1r,'Тренируйся')
}, 1500)

setTimeout(() => {
    addStyleTo(h2r, 'Ииииии', 'blue')
}, 3000)

const link = h3r.children[0]

//отменяем стандартное поведение
link.addEventListener('click', (event) => {
    event.preventDefault()
    console.log('Click on link', event.target.getAttribute('href'))
    const url = event.target.getAttribute('href')

    window.location = url
})
//const link = h3r.querySelector('a')

setTimeout(() => {
    addStyleTo(link, 'Будешь крут!', "white")
}, 4500)

h1r.onclick = () => {
    h1r.textContent = 'dasdasdf'
}

h2r.addEventListener('dblclick', () => {
    if (h2r.style.color === 'blue') {
        h2r.style.color = 'white'
    } else {
        h2r.style.color = 'blue'
    }
})



function addStyleTo(node, text, color = 'red') {
    node.textContent = text
    node.style.color = color
    node.style.textAlign = 'center'
    node.style.backgroundColor = 'black'
    node.style.padding = '2rem'
    node.style.display = 'block'
    node.style.width = '100%'
}
