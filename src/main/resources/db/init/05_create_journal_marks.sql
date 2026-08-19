CREATE TABLE journal_marks (
journal_entry_id BIGSERIAL PRIMARY KEY,
journal_id BIGINT NOT NULL,
student_id BIGINT NOT NULL,
mark INTEGER NOT NULL,
date TIMESTAMP NOT NULL,
CONSTRAINT fk_entry_journal
FOREIGN KEY (journal_id)
REFERENCES journals(journal_id)
ON DELETE CASCADE,
CONSTRAINT fk_entry_student
FOREIGN KEY (student_id)
REFERENCES students(student_id)
ON DELETE CASCADE,
CONSTRAINT chk_mark
CHECK (mark BETWEEN 0 AND 100)
);