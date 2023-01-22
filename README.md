# RealEstateAgency
 
## Purpose
The app serves as a listing agency for people selling properties.

## Entities
1. User entity: represents the user
2. Role entity: represents one of the three roles that the user can have: AGENT, OWNER and BIDDER. 
3. Property entity: represents the property (a house or flat) that can be listed
4. Listing entity: represents the listing for a property. An agent cand add a listing and other users can bid on a listing
5. Bid entity: represents the bid made for a listing
6. Review entity: represents the review for a listing
7. JP_AUDIT: stores actions performed (logs)

## Bussiness requirements
1. As an agent, I would like to be able to add a new listing.
2. As an owner, I would like to add a new property that is available.
3. As a user, I would like to be able to bid on an available listing.
4. As a user, I would like to see all the listings available. 
5. As a user, I would like to be able to live a review on a listing.
