CREATE TABLE journals (
journal_id BIGSERIAL PRIMARY KEY,
study_year VARCHAR(20) NOT NULL,
group_id BIGINT NOT NULL,
subject_id BIGINT NOT NULL,

CONSTRAINT fk_journal_group
FOREIGN KEY (group_id)
REFERENCES groups(group_id)
ON DELETE CASCADE,
CONSTRAINT fk_journal_subject
FOREIGN KEY (subject_id)
REFERENCES subjects(subject_id)
ON DELETE CASCADE,
CONSTRAINT uq_journal_group_subject
UNIQUE (group_id, subject_id)
);