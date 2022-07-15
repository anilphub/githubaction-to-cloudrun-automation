****** Setup Workload Identity Federation************

export PROJECT_ID="anil-etalk-test"

1. Create a new workload identity pool:

gcloud iam workload-identity-pools create "my-pool1" \
  --project="${PROJECT_ID}" \
  --location="global" \
  --display-name="Demo pool1"

gcloud iam workload-identity-pools describe "my-pool1" \
  --project="${PROJECT_ID}" \
  --location="global" \
  --format="value(name)"

export WORKLOAD_IDENTITY_POOL_ID=projects/932227783844/locations/global/workloadIdentityPools/my-pool1

2. Add a workload identity pool provider:

gcloud iam workload-identity-pools providers create-oidc "my-provider1" \
  --project="${PROJECT_ID}" \
  --location="global" \
  --workload-identity-pool="my-pool1" \
  --display-name="Demo provider1" \
  --attribute-mapping="google.subject=assertion.sub,attribute.actor=assertion.actor,attribute.repository=assertion.repository" \
  --attribute-mapping="google.subject=assertion.sub,attribute.repository_owner=assertion.repository_owner" \
  --issuer-uri="https://token.actions.githubusercontent.com"
  

export REPO="anilphub/googlepoctest1"

gcloud iam service-accounts add-iam-policy-binding "akp-devnxt-sa@anil-etalk-test.iam.gserviceaccount.com" \
  --project="${PROJECT_ID}" \
  --role="roles/iam.workloadIdentityUser" \
  --member="principalSet://iam.googleapis.com/${WORKLOAD_IDENTITY_POOL_ID}/attribute.repository/${REPO}"


gcloud iam workload-identity-pools providers describe "my-provider1" \
  --project="${PROJECT_ID}" \
  --location="global" \
  --workload-identity-pool="my-pool1" \
  --format="value(name)"
  
workload_identity_provider = projects/932227783844/locations/global/workloadIdentityPools/my-pool1/providers/my-provider1

***********************************END******************************************************



One of the Common Error:
Error: google-github-actions/auth failed with: retry function failed with 0 attempts: failed to generate Google Cloud access token for ***:
{
  "error": {
    "code": 403,
    "message": "The caller does not have permission",
    "status": "PERMISSION_DENIED"
  }
}

Screen Shot - 

![image](https://user-images.githubusercontent.com/42490682/170505639-a32d3a94-3e54-430c-9623-f860505ec267.png)

Solution : 
Check Attribute mapping as per below screen shot.
![image](https://user-images.githubusercontent.com/42490682/170504807-b3d3f7c1-d7a1-4898-a8d7-4cc681b333b3.png)

Problem : Storage Error while executing githubaction for cloud run deployment
Solution : Make sure that service account has below permission per screen shot

![image](https://user-images.githubusercontent.com/42490682/170511909-8ef1cdd4-acf3-4cfd-843b-36207318ad35.png)
