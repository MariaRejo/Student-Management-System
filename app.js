// Idhu than FRONTEND JS. Idhu namba Spring Boot backend oda
// REST API ah "fetch()" vachi call pannum. Same origin (localhost:8080)
// la irundhu than call panrom, so CORS problem varathu.

const API_URL = '/api/students';

const form = document.getElementById('studentForm');
const tableBody = document.getElementById('studentTableBody');
const emptyMsg = document.getElementById('emptyMsg');
const formTitle = document.getElementById('formTitle');
const submitBtn = document.getElementById('submitBtn');
const cancelBtn = document.getElementById('cancelBtn');
const studentIdField = document.getElementById('studentId');
const searchInput = document.getElementById('searchInput');

// Page load aana odane, ella students-um fetch pannu
document.addEventListener('DOMContentLoaded', loadStudents);

// Form submit pannumbothu - Add or Update
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

// Ella students-um fetch panni table la kaatu
async function loadStudents() {
    try {
        const response = await fetch(API_URL);
        const students = await response.json();
        renderTable(students);
    } catch (err) {
        showToast('Could not load students. Is the backend running?', true);
    }
}

// Table row-a render pannu
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

// Edit button click pannumbothu, form la data fill pannu
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

// Delete button click pannumbothu
async function deleteStudent(id) {
    if (!confirm('Sure ah delete panna?')) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });
        if (!response.ok) throw new Error('Delete failed');
        showToast('Student deleted!');
        loadStudents();
    } catch (err) {
        showToast(err.message, true);
    }
}

// Form ah reset panni "Add" mode ku thirumba varum
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

// Toast message kaatu (bottom la small popup)
function showToast(message, isError = false) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.className = 'toast show' + (isError ? ' error' : '');
    setTimeout(() => { toast.className = 'toast'; }, 2500);
}

// XSS thadukka - HTML characters ah escape pannu
function escapeHtml(str) {
    const div = document.createElement('div');
    div.textContent = str;
    return div.innerHTML;
}
