package gov.lbl.webjob.ent;

import java.util.Date;

import org.mongodb.morphia.annotations.*;

import gov.lbl.webjob.util.Util;

@Entity("tempStorage")
public class TempStorage {
	
	@Id private String objectId;
	public String storageId;
	public Date created;
	public Date lastAccessed;
	public boolean okToDelete; //in case something needs to remain in the temporary storage long enough to differentiate it from non-deleted files.
	
	//Morphia CANNOT access generic Object
	@NotSaved public Object itemToStore;
	
	TempStorage(){
		//Empty constructor for Morphia
	}
	
	public TempStorage(Object itemToStore){
		this.itemToStore = itemToStore;
		storageId = Util.genStringUUID();
		created = Util.genTimestamp();
		lastAccessed = Util.genTimestamp();
		okToDelete = false;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastAccessed() {
		return lastAccessed;
	}

	public void setLastAccessed(Date lastAccessed) {
		this.lastAccessed = lastAccessed;
	}

	public boolean isOkToDelete() {
		return okToDelete;
	}

	public void setOkToDelete(boolean okToDelete) {
		this.okToDelete = okToDelete;
	}

	public Object getItemToStore() {
		return itemToStore;
	}

	public void setItemToStore(Object itemToStore) {
		this.itemToStore = itemToStore;
	}
	
}
