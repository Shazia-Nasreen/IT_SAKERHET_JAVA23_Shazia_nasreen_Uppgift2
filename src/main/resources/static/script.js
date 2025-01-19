let authToken = null;

async function register() {
    const email = document.getElementById('register-email').value;
    const password = document.getElementById('register-password').value;

    const response = await fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password })
    });

    const message = await response.text();
    document.getElementById('register-message').innerText = message;
}

async function login() {
    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    const response = await fetch('/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email, password })
    });

    if (response.ok) {
        authToken = await response.text();  // Save the JWT token
        document.getElementById('login-message').innerText = "Login successful!";

        document.getElementById('capsule-section').style.display = 'block';
        document.getElementById('view-capsules').style.display = 'block';
    } else {
        document.getElementById('login-message').innerText = "Login failed!";
    }
}

async function createCapsule() {
    const message = document.getElementById('capsule-message').value;

    const response = await fetch('/capsules/create', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': authToken // Attach JWT token
        },
        body: message
    });

    const messageStatus = await response.text();
    document.getElementById('capsule-message-status').innerText = messageStatus;
}

async function readCapsules() {
    const response = await fetch('/capsules/read', {
        method: 'GET',
        headers: {
            'Authorization': authToken // Attach JWT token
        }
    });

    const capsules = await response.json();

    const container = document.getElementById('capsules-container');
    container.innerHTML = '';

    if (capsules.length > 0) {
        capsules.forEach(capsule => {
            const div = document.createElement('div');
            div.textContent = capsule;
            container.appendChild(div);
        });
    } else {
        container.innerText = 'No capsules found';
    }
}
