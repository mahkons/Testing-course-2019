package ru.makhnev;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Issue {

    @NotNull private final String summary;
    @NotNull private final String description;

    public Issue(String summary, String description) {
        this.summary = summary;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return summary.equals(issue.summary) &&
                description.equals(issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(summary, description);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }
}
