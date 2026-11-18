// This is the FRONTEND JS. It calls our Spring Boot backend's
// REST API using fetch(). Since requests go to the same origin
// (localhost:8080), there are no CORS problems.

const API_URL = '/api/students';

const form = document.getElementById('studentForm');
const tableBody = document.getElementById('studentTableBody');
const emptyMsg = document.getElementById('emptyMsg');
const formTitle = document.getElementById('formTitle');
const submitBtn = document.getElementById('submitBtn');
const cancelBtn = document.getElementById('cancelBtn');
const studentIdField = document.getElementById('studentId');
const searchInput = document.getElementById('searchInput');

// As soon as the page loads, fetch all students
document.addEventListener('DOMContentLoaded', loadStudents);

// On form submit - Add or Update
form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const student = {
        name: document.getElementById('name').value.trim(),
        email: document.getElementById('email').value.trim(),
        department: document.getElementById('department').value.trim(),
        age: document.getElementById('age').value ? Number(document.getElementById('age').value) : null,
        marks: document.getElementById('marks').value ? Number(document.getElementById('marks').value) : null
    };

    const id = studentIdField.value;

    try {
        let response;
        if (id) {
            // UPDATE - PUT request
            response = await fetch(`${API_URL}/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(student)
            });
        } else {
            // CREATE - POST request
            response = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(student)
            });
        }

        if (!response.ok) throw new Error('Save failed. Check all fields are correct.');

        showToast(id ? 'Student updated!' : 'Student added!');
        resetForm();
        loadStudents();
    } catch (err) {
        showToast(err.message, true);
    }
});

cancelBtn.addEventListener('click', resetForm);

// Fetch all students and show them in the table
async function loadStudents() {
    try {
        const response = await fetch(API_URL);
        const students = await response.json();
        renderTable(students);
    } catch (err) {
        showToast('Could not load students. Is the backend running?', true);
    }
}

// Render the table rows
function renderTable(students) {
    tableBody.innerHTML = '';

    if (students.length === 0) {
        emptyMsg.style.display = 'block';
        return;
    }
    emptyMsg.style.display = 'none';

    students.forEach(s => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${s.id}</td>
            <td>${escapeHtml(s.name)}</td>
            <td>${escapeHtml(s.email)}</td>
            <td>${escapeHtml(s.department)}</td>
            <td>${s.age ?? '-'}</td>
            <td>${s.marks ?? '-'}</td>
            <td class="actions-cell">
                <button class="btn btn-edit" onclick='editStudent(${JSON.stringify(s)})'>Edit</button>
                <button class="btn btn-delete" onclick='deleteStudent(${s.id})'>Delete</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// When the Edit button is clicked, fill the form with the student's data
function editStudent(student) {
    studentIdField.value = student.id;
    document.getElementById('name').value = student.name;
    document.getElementById('email').value = student.email;
    document.getElementById('department').value = student.department;
    document.getElementById('age').value = student.age ?? '';
    document.getElementById('marks').value = student.marks ?? '';

    formTitle.textContent = 'Edit Student';
    submitBtn.textContent = 'Update Student';
    cancelBtn.style.display = 'inline-block';
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

// When the Delete button is clicked
async function deleteStudent(id) {
    if (!confirm('Are you sure you want to delete this student?')) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('Delete failed');
        showToast('Student deleted!');
        loadStudents();
    } catch (err) {
        showToast(err.message, true);
    }
}

// Reset the form and switch back to "Add" mode
function resetForm() {
    form.reset();
    studentIdField.value = '';
    formTitle.textContent = 'Add New Student';
    submitBtn.textContent = 'Add Student';
    cancelBtn.style.display = 'none';
}

// Search functionality
document.getElementById('searchBtn').addEventListener('click', searchStudents);
document.getElementById('clearBtn').addEventListener('click', () => {
    searchInput.value = '';
    loadStudents();
});
searchInput.addEventListener('keyup', (e) => {
    if (e.key === 'Enter') searchStudents();
});

async function searchStudents() {
    const name = searchInput.value.trim();
    if (!name) { loadStudents(); return; }

    try {
        const response = await fetch(`${API_URL}/search?name=${encodeURIComponent(name)}`);
        const students = await response.json();
        renderTable(students);
    } catch (err) {
        showToast('Search failed', true);
    }
}

// Show a toast message (small popup at the bottom)
function showToast(message, isError = false) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = 'toast show' + (isError ? ' error' : '');
    setTimeout(() => { toast.className = 'toast'; }, 2500);
}

// Prevent XSS - escape HTML characters
function escapeHtml(str) {
    const div = document.createElement('div');
    div.textContent = str;
    return div.innerHTML;
}
