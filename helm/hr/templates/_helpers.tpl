{{/*
Expand the name of the chart.
*/}}
{{- define "hr.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "hr.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "hr.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "hr.labels" -}}
helm.sh/chart: {{ include "hr.chart" . }}
{{ include "hr.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "hr.selectorLabels" -}}
app.kubernetes.io/name: {{ include "hr.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "hr.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "hr.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}

{{- define "hr.env" -}}
- name: SPRING_PROFILES_ACTIVE
  value: default
- name: PORT
  value: '8080'
- name: DATASOURCE_URL
  value: jdbc:postgresql://api-postgresql:5432/{{ .Values.postgresql.auth.database }}
{{- if .Values.postgresql.auth.username }}
- name: DATASOURCE_USER
  value: {{ .Values.postgresql.auth.username }}
{{- else }}
- name: DATASOURCE_USER
  value: postgres
{{- end }}
{{- if .Values.postgresql.auth.password }}
- name: DATASOURCE_PASSWORD
  value: {{ .Values.postgresql.auth.password }}
{{- else }}
- name: DATASOURCE_PASSWORD
  valueFrom:
    secretKeyRef:
      name: api-postgresql
      key: postgres-password
{{- end }}
- name: TOKEN_SECRET
  value: asd1231fsasf1231asds123asd123
{{- end }}
