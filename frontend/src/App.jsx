import React, { useEffect, useState } from 'react';

const API = 'http://localhost:8080/api';

async function request(url, options = {}) {
  const res = await fetch(url, {
    headers: {
      'Content-Type': 'application/json',
    },
    ...options,
  });

  if (!res.ok) {
    const text = await res.text();
    throw new Error(text || `Request failed (${res.status})`);
  }

  if (res.status === 204) return null;

  return res.json();
}

function App() {
  const [tab, setTab] = useState('students');
  const [students, setStudents] = useState([]);
  const [subjects, setSubjects] = useState([]);
  const [search, setSearch] = useState('');
  const [editing, setEditing] = useState(null);
  const [message, setMessage] = useState('');
  const [selectedStudent, setSelectedStudent] = useState(null);

  // =========================
  // LOAD STUDENTS
  // =========================
  const loadStudents = async (q = '') => {
    try {
      const url = q
        ? `${API}/students?search=${encodeURIComponent(q)}`
        : `${API}/students`;

      const data = await request(url);

      setStudents(data);
    } catch (e) {
      showError(e);
    }
  };

  // =========================
  // LOAD INITIAL DATA
  // =========================
  useEffect(() => {
    loadStudents();

    request(`${API}/subjects`)
      .then(setSubjects)
      .catch(showError);
  }, []);

  // =========================
  // ERROR MESSAGE
  // =========================
  function showError(e) {
    console.error(e);
    setMessage(`Error: ${e.message}`);
  }

  // =========================
  // SUCCESS MESSAGE
  // =========================
  function showSuccess(text) {
    setMessage(text);

    setTimeout(() => {
      setMessage('');
    }, 2500);
  }

  // =========================
  // ADD / UPDATE STUDENT
  // =========================
  async function saveStudent(form) {
    try {
      if (editing) {
        await request(`${API}/students/${editing.id}`, {
          method: 'PUT',
          body: JSON.stringify(form),
        });

        setEditing(null);
        await loadStudents(search);

        showSuccess('Student updated successfully.');
      } else {
        await request(`${API}/students`, {
          method: 'POST',
          body: JSON.stringify(form),
        });

        await loadStudents(search);

        showSuccess('Student added successfully.');
      }
    } catch (e) {
      showError(e);
    }
  }

  // =========================
  // DELETE STUDENT
  // =========================
  async function deleteStudent(id) {
    if (
      !window.confirm(
        'Delete this student? Their attendance will also be deleted.'
      )
    ) {
      return;
    }

    try {
      await request(`${API}/students/${id}`, {
        method: 'DELETE',
      });

      await loadStudents(search);

      showSuccess('Student deleted successfully.');
    } catch (e) {
      showError(e);
    }
  }

  // =========================
  // OPEN ATTENDANCE
  // =========================
  function openAttendance(student) {
    setSelectedStudent(student);
    setTab('attendance');
  }

  // =========================
  // BACK TO STUDENTS
  // =========================
  function backToStudents() {
    setTab('students');

    // Reload students when coming back
    loadStudents(search);

    // Clear selected student
    setSelectedStudent(null);
  }

  return (
    <div className="app">
      {/* ================= HEADER ================= */}
      <header className="topbar">
        <div>
          <h1>Student Management System</h1>
          <p>Java + JDBC + MySQL + React</p>
        </div>
      </header>

      {/* ================= NAVIGATION ================= */}
      <nav className="nav">
        <button
          className={tab === 'students' ? 'active' : ''}
          onClick={() => {
            setTab('students');
            loadStudents(search);
          }}
        >
          Students
        </button>

        <button
          className={tab === 'attendance' ? 'active' : ''}
          onClick={() => setTab('attendance')}
          disabled={!selectedStudent}
        >
          Attendance
          {selectedStudent ? ` — ${selectedStudent.name}` : ''}
        </button>
      </nav>

      {/* ================= MESSAGE ================= */}
      {message && (
        <div
          className={
            message.startsWith('Error')
              ? 'alert error'
              : 'alert'
          }
        >
          {message}
        </div>
      )}

      {/* ================= MAIN ================= */}
      <main className="container">

        {/* ==================================================
            STUDENTS PAGE
        ================================================== */}
        {tab === 'students' && (
          <>
            {/* ADD / UPDATE STUDENT */}
            <section className="card">
              <div className="section-title">
                <h2>
                  {editing ? 'Update Student' : 'Add Student'}
                </h2>

                {editing && (
                  <button
                    className="secondary"
                    onClick={() => setEditing(null)}
                  >
                    Cancel
                  </button>
                )}
              </div>

              <StudentForm
                initial={editing}
                onSave={saveStudent}
              />
            </section>

            {/* STUDENT LIST */}
            <section className="card">
              <div className="section-title">
                <h2>Students</h2>

                <span className="count">
                  {students.length} student(s)
                </span>
              </div>

              {/* SEARCH */}
              <div className="search">
                <input
                  value={search}
                  onChange={(e) =>
                    setSearch(e.target.value)
                  }
                  placeholder="Search by name, email or course"
                />

                <button
                  onClick={() =>
                    loadStudents(search)
                  }
                >
                  Search
                </button>

                <button
                  className="secondary"
                  onClick={() => {
                    setSearch('');
                    loadStudents();
                  }}
                >
                  Clear
                </button>
              </div>

              {/* TABLE */}
              <StudentTable
                students={students}
                onEdit={setEditing}
                onDelete={deleteStudent}
                onAttendance={openAttendance}
              />
            </section>
          </>
        )}

        {/* ==================================================
            ATTENDANCE PAGE
        ================================================== */}
        {tab === 'attendance' && selectedStudent && (
          <AttendancePage
            student={selectedStudent}
            subjects={subjects}
            onBack={backToStudents}
            onMessage={showSuccess}
            onError={showError}
          />
        )}

      </main>
    </div>
  );
}

// ======================================================
// STUDENT FORM
// ======================================================

function StudentForm({ initial, onSave }) {
  const blank = {
    name: '',
    email: '',
    phone: '',
    course: '',
    year: '',
  };

  const [form, setForm] = useState(
    initial || blank
  );

  useEffect(() => {
    setForm(initial || blank);
  }, [initial]);

  function change(e) {
    setForm({
      ...form,
      [e.target.name]: e.target.value,
    });
  }

  function submit(e) {
    e.preventDefault();

    onSave({
      ...form,
      year: Number(form.year),
    });
  }

  return (
    <form
      className="form-grid"
      onSubmit={submit}
    >
      <input
        name="name"
        value={form.name}
        onChange={change}
        placeholder="Name"
        required
      />

      <input
        name="email"
        type="email"
        value={form.email}
        onChange={change}
        placeholder="Email"
        required
      />

      <input
        name="phone"
        value={form.phone}
        onChange={change}
        placeholder="Phone"
      />

      <input
        name="course"
        value={form.course}
        onChange={change}
        placeholder="Course (e.g. CSE)"
        required
      />

      <input
        name="year"
        type="number"
        min="1"
        max="6"
        value={form.year}
        onChange={change}
        placeholder="Year"
        required
      />

      <button type="submit">
        {initial
          ? 'Update Student'
          : 'Add Student'}
      </button>
    </form>
  );
}

// ======================================================
// STUDENT TABLE
// ======================================================

function StudentTable({
  students,
  onEdit,
  onDelete,
  onAttendance,
}) {
  if (!students.length) {
    return (
      <p className="empty">
        No students found.
      </p>
    );
  }

  return (
    <div className="table-wrap">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Course</th>
            <th>Year</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {students.map((s) => (
            <tr key={s.id}>
              <td>{s.id}</td>

              <td>{s.name}</td>

              <td>{s.email}</td>

              <td>
                {s.phone || '-'}
              </td>

              <td>{s.course}</td>

              <td>{s.year}</td>

              <td className="actions">

                <button
                  onClick={() =>
                    onAttendance(s)
                  }
                >
                  Attendance
                </button>

                <button
                  className="secondary"
                  onClick={() =>
                    onEdit(s)
                  }
                >
                  Edit
                </button>

                <button
                  className="danger"
                  onClick={() =>
                    onDelete(s.id)
                  }
                >
                  Delete
                </button>

              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

// ======================================================
// ATTENDANCE PAGE
// ======================================================

function AttendancePage({
  student,
  subjects,
  onBack,
  onMessage,
  onError,
}) {
  const blank = {
    subjectId: '',
    totalClasses: '',
    attendedClasses: '',
  };

  const [rows, setRows] = useState([]);
  const [form, setForm] = useState(blank);
  const [editing, setEditing] = useState(null);

  // =========================
  // LOAD ATTENDANCE
  // =========================
  const load = async () => {
    try {
      const data = await request(
        `${API}/attendance/student/${student.id}`
      );

      setRows(data);
    } catch (e) {
      onError(e);
    }
  };

  // =========================
  // LOAD WHEN STUDENT CHANGES
  // =========================
  useEffect(() => {
    load();
  }, [student.id]);

  // =========================
  // ADD / UPDATE ATTENDANCE
  // =========================
  async function submit(e) {
    e.preventDefault();

    if (
      Number(form.attendedClasses) >
      Number(form.totalClasses)
    ) {
      onError(
        new Error(
          'Attended classes cannot be greater than total classes.'
        )
      );
      return;
    }

    const body = {
      studentId: student.id,
      subjectId: Number(form.subjectId),
      totalClasses: Number(form.totalClasses),
      attendedClasses: Number(
        form.attendedClasses
      ),
    };

    try {
      if (editing) {
        await request(
          `${API}/attendance/${editing.id}`,
          {
            method: 'PUT',
            body: JSON.stringify(body),
          }
        );
      } else {
        await request(
          `${API}/attendance`,
          {
            method: 'POST',
            body: JSON.stringify(body),
          }
        );
      }

      setForm(blank);
      setEditing(null);

      await load();

      onMessage(
        editing
          ? 'Attendance updated.'
          : 'Attendance added.'
      );
    } catch (e) {
      onError(e);
    }
  }

  // =========================
  // EDIT ATTENDANCE
  // =========================
  function edit(row) {
    setEditing(row);

    setForm({
      subjectId: row.subjectId,
      totalClasses: row.totalClasses,
      attendedClasses:
        row.attendedClasses,
    });
  }

  // =========================
  // DELETE ATTENDANCE
  // =========================
  async function del(id) {
    if (
      !window.confirm(
        'Delete this attendance record?'
      )
    ) {
      return;
    }

    try {
      await request(
        `${API}/attendance/${id}`,
        {
          method: 'DELETE',
        }
      );

      await load();

      onMessage(
        'Attendance deleted.'
      );
    } catch (e) {
      onError(e);
    }
  }

  return (
    <>
      {/* ================= ATTENDANCE FORM ================= */}
      <section className="card">

        <div className="section-title">

          <div>
            <h2>
              Attendance — {student.name}
            </h2>

            <p>
              {student.course}, Year {student.year}
            </p>
          </div>

          <button
            className="secondary"
            onClick={onBack}
          >
            Back to Students
          </button>

        </div>

        <form
          className="form-grid"
          onSubmit={submit}
        >

          <select
            value={form.subjectId}
            onChange={(e) =>
              setForm({
                ...form,
                subjectId:
                  e.target.value,
              })
            }
            required
          >
            <option value="">
              Select subject
            </option>

            {subjects.map((x) => (
              <option
                key={x.id}
                value={x.id}
              >
                {x.name} ({x.course})
              </option>
            ))}
          </select>

          <input
            type="number"
            min="0"
            value={form.totalClasses}
            onChange={(e) =>
              setForm({
                ...form,
                totalClasses:
                  e.target.value,
              })
            }
            placeholder="Total classes"
            required
          />

          <input
            type="number"
            min="0"
            value={form.attendedClasses}
            onChange={(e) =>
              setForm({
                ...form,
                attendedClasses:
                  e.target.value,
              })
            }
            placeholder="Attended classes"
            required
          />

          <button type="submit">
            {editing
              ? 'Update Attendance'
              : 'Add Attendance'}
          </button>

          {editing && (
            <button
              type="button"
              className="secondary"
              onClick={() => {
                setEditing(null);
                setForm(blank);
              }}
            >
              Cancel
            </button>
          )}

        </form>
      </section>

      {/* ================= ATTENDANCE RECORDS ================= */}
      <section className="card">

        <h2>
          Attendance Records
        </h2>

        {!rows.length ? (
          <p className="empty">
            No attendance records yet.
          </p>
        ) : (
          <div className="table-wrap">

            <table>

              <thead>
                <tr>
                  <th>Subject</th>
                  <th>Total</th>
                  <th>Attended</th>
                  <th>Percentage</th>
                  <th>Actions</th>
                </tr>
              </thead>

              <tbody>

                {rows.map((r) => (
                  <tr key={r.id}>

                    <td>
                      {r.subjectName}
                    </td>

                    <td>
                      {r.totalClasses}
                    </td>

                    <td>
                      {r.attendedClasses}
                    </td>

                    <td>
                      <strong>
                        {Number(
                          r.percentage
                        ).toFixed(2)}
                        %
                      </strong>
                    </td>

                    <td className="actions">

                      <button
                        className="secondary"
                        onClick={() =>
                          edit(r)
                        }
                      >
                        Edit
                      </button>

                      <button
                        className="danger"
                        onClick={() =>
                          del(r.id)
                        }
                      >
                        Delete
                      </button>

                    </td>

                  </tr>
                ))}

              </tbody>

            </table>

          </div>
        )}

      </section>
    </>
  );
}

export default App;