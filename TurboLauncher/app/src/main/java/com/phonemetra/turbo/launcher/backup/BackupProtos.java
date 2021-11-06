package com.phonemetra.turbo.launcher.backup;

public final class BackupProtos {
	

	  private BackupProtos() {}
	  public static final class Key extends
	      com.google.protobuf.nano.MessageNano {
	    public static final Key EMPTY_ARRAY[] = {};
	    public Key() {}
	    
	    // enum Type
	    public static final int FAVORITE = 1;
	    public static final int SCREEN = 2;
	    public static final int ICON = 3;
	    public static final int WIDGET = 4;
	    
	    // required .launcher_backup.Key.Type type = 1;
	    public int type = FAVORITE;
	    
	    // optional string name = 2;
	    public java.lang.String name = "";
	    
	    // optional int64 id = 3;
	    public long id = 0L;
	    
	    // optional int64 checksum = 4;
	    public long checksum = 0L;
	    
	    public final Key clear() {
	      type = FAVORITE;
	      name = "";
	      id = 0L;
	      checksum = 0L;
	      cachedSize = -1;
	      return this;
	    }

	    @Override
	    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
	                        throws java.io.IOException {
	      output.writeInt32(1, this.type);
	      if (!this.name.equals("")) {
	        output.writeString(2, this.name);
	      }
	      if (this.id != 0L) {
	        output.writeInt64(3, this.id);
	      }
	      if (this.checksum != 0L) {
	        output.writeInt64(4, this.checksum);
	      }
	    }
	    
	    private int cachedSize = -1;
	    @Override
	    public int getCachedSize() {
	      if (cachedSize < 0) {
	        // getSerializedSize sets cachedSize
	        getSerializedSize();
	      }
	      return cachedSize;
	    }

	    @Override
	    public int getSerializedSize() {
	      int size = 0;
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	        .computeInt32Size(1, this.type);
	      if (!this.name.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(2, this.name);
	      }
	      if (this.id != 0L) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt64Size(3, this.id);
	      }
	      if (this.checksum != 0L) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt64Size(4, this.checksum);
	      }
	      cachedSize = size;
	      return size;
	    }
	    
	    @Override
	    public Key mergeFrom(
	        com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      while (true) {
	        int tag = input.readTag();
	        switch (tag) {
	          case 0:
	            return this;
	          default: {
	            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
	              return this;
	            }
	            break;
	          }
	          case 8: {
	              this.type = input.readInt32();
	            break;
	          }
	          case 18: {
	            this.name = input.readString();
	            break;
	          }
	          case 24: {
	            this.id = input.readInt64();
	            break;
	          }
	          case 32: {
	            this.checksum = input.readInt64();
	            break;
	          }
	        }
	      }
	    }
	    
	    public static Key parseFrom(byte[] data)
	        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
	      return com.google.protobuf.nano.MessageNano.mergeFrom(new Key(), data);
	    }
	    
	    public static Key parseFrom(
	            com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      return new Key().mergeFrom(input);
	    }
	    
	  }
	  
	  @SuppressWarnings("hiding")
	  public static final class CheckedMessage extends
	      com.google.protobuf.nano.MessageNano {
	    public static final CheckedMessage EMPTY_ARRAY[] = {};
	    public CheckedMessage() {}
	    
	    // required bytes payload = 1;
	    public byte[] payload = com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES;
	    
	    // required int64 checksum = 2;
	    public long checksum = 0L;
	    
	    public final CheckedMessage clear() {
	      payload = com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES;
	      checksum = 0L;
	      cachedSize = -1;
	      return this;
	    }
	    
	    @Override
	    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
	                        throws java.io.IOException {
	      output.writeBytes(1, this.payload);
	      output.writeInt64(2, this.checksum);
	    }
	    
	    private int cachedSize = -1;
	    @Override
	    public int getCachedSize() {
	      if (cachedSize < 0) {
	        // getSerializedSize sets cachedSize
	        getSerializedSize();
	      }
	      return cachedSize;
	    }
	    
	    @Override
	    public int getSerializedSize() {
	      int size = 0;
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeBytesSize(1, this.payload);
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeInt64Size(2, this.checksum);
	      cachedSize = size;
	      return size;
	    }
	    
	    @Override
	    public CheckedMessage mergeFrom(
	        com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      while (true) {
	        int tag = input.readTag();
	        switch (tag) {
	          case 0:
	            return this;
	          default: {
	            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
	              return this;
	            }
	            break;
	          }
	          case 10: {
	            this.payload = input.readBytes();
	            break;
	          }
	          case 16: {
	            this.checksum = input.readInt64();
	            break;
	          }
	        }
	      }
	    }
	    
	    public static CheckedMessage parseFrom(byte[] data)
	        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
	      return com.google.protobuf.nano.MessageNano.mergeFrom(new CheckedMessage(), data);
	    }
	    
	    public static CheckedMessage parseFrom(
	            com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      return new CheckedMessage().mergeFrom(input);
	    }
	    
	  }
	  
	  @SuppressWarnings("hiding")
	  public static final class Journal extends
	      com.google.protobuf.nano.MessageNano {
	    public static final Journal EMPTY_ARRAY[] = {};
	    public Journal() {}
	    
	    // required int32 app_version = 1;
	    public int appVersion = 0;
	    
	    // required int64 t = 2;
	    public long t = 0L;
	    
	    // optional int64 bytes = 3;
	    public long bytes = 0L;
	    
	    // optional int32 rows = 4;
	    public int rows = 0;
	    
	    // repeated .launcher_backup.Key key = 5;
	    public com.phonemetra.turbo.launcher.backup.BackupProtos.Key[] key = com.phonemetra.turbo.launcher.backup.BackupProtos.Key.EMPTY_ARRAY;
	    
	    public final Journal clear() {
	      appVersion = 0;
	      t = 0L;
	      bytes = 0L;
	      rows = 0;
	      key = com.phonemetra.turbo.launcher.backup.BackupProtos.Key.EMPTY_ARRAY;
	      cachedSize = -1;
	      return this;
	    }
	    
	    @Override
	    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
	                        throws java.io.IOException {
	      output.writeInt32(1, this.appVersion);
	      output.writeInt64(2, this.t);
	      if (this.bytes != 0L) {
	        output.writeInt64(3, this.bytes);
	      }
	      if (this.rows != 0) {
	        output.writeInt32(4, this.rows);
	      }
	      for (com.phonemetra.turbo.launcher.backup.BackupProtos.Key element : this.key) {
	        output.writeMessage(5, element);
	      }
	    }
	    
	    private int cachedSize = -1;
	    @Override
	    public int getCachedSize() {
	      if (cachedSize < 0) {
	        // getSerializedSize sets cachedSize
	        getSerializedSize();
	      }
	      return cachedSize;
	    }
	    
	    @Override
	    public int getSerializedSize() {
	      int size = 0;
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeInt32Size(1, this.appVersion);
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeInt64Size(2, this.t);
	      if (this.bytes != 0L) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt64Size(3, this.bytes);
	      }
	      if (this.rows != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(4, this.rows);
	      }
	      for (com.phonemetra.turbo.launcher.backup.BackupProtos.Key element : this.key) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeMessageSize(5, element);
	      }
	      cachedSize = size;
	      return size;
	    }
	    
	    @Override
	    public Journal mergeFrom(
	        com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      while (true) {
	        int tag = input.readTag();
	        switch (tag) {
	          case 0:
	            return this;
	          default: {
	            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
	              return this;
	            }
	            break;
	          }
	          case 8: {
	            this.appVersion = input.readInt32();
	            break;
	          }
	          case 16: {
	            this.t = input.readInt64();
	            break;
	          }
	          case 24: {
	            this.bytes = input.readInt64();
	            break;
	          }
	          case 32: {
	            this.rows = input.readInt32();
	            break;
	          }
	          case 42: {
	            int arrayLength = com.google.protobuf.nano.WireFormatNano.getRepeatedFieldArrayLength(input, 42);
	            int i = this.key.length;
	            com.phonemetra.turbo.launcher.backup.BackupProtos.Key[] newArray = new com.phonemetra.turbo.launcher.backup.BackupProtos.Key[i + arrayLength];
	            System.arraycopy(this.key, 0, newArray, 0, i);
	            this.key = newArray;
	            for (; i < this.key.length - 1; i++) {
	              this.key[i] = new com.phonemetra.turbo.launcher.backup.BackupProtos.Key();
	              input.readMessage(this.key[i]);
	              input.readTag();
	            }
	            // Last one without readTag.
	            this.key[i] = new com.phonemetra.turbo.launcher.backup.BackupProtos.Key();
	            input.readMessage(this.key[i]);
	            break;
	          }
	        }
	      }
	    }
	    
	    public static Journal parseFrom(byte[] data)
	        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
	      return com.google.protobuf.nano.MessageNano.mergeFrom(new Journal(), data);
	    }
	    
	    public static Journal parseFrom(
	            com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      return new Journal().mergeFrom(input);
	    }
	    
	  }
	  
	  @SuppressWarnings("hiding")
	  public static final class Favorite extends
	      com.google.protobuf.nano.MessageNano {
	    public static final Favorite EMPTY_ARRAY[] = {};
	    public Favorite() {}
	    
	    // required int64 id = 1;
	    public long id = 0L;
	    
	    // required int32 itemType = 2;
	    public int itemType = 0;
	    
	    // optional string title = 3;
	    public java.lang.String title = "";
	    
	    // optional int32 container = 4;
	    public int container = 0;
	    
	    // optional int32 screen = 5;
	    public int screen = 0;
	    
	    // optional int32 cellX = 6;
	    public int cellX = 0;
	    
	    // optional int32 cellY = 7;
	    public int cellY = 0;
	    
	    // optional int32 spanX = 8;
	    public int spanX = 0;
	    
	    // optional int32 spanY = 9;
	    public int spanY = 0;
	    
	    // optional int32 displayMode = 10;
	    public int displayMode = 0;
	    
	    // optional int32 appWidgetId = 11;
	    public int appWidgetId = 0;
	    
	    // optional string appWidgetProvider = 12;
	    public java.lang.String appWidgetProvider = "";
	    
	    // optional string intent = 13;
	    public java.lang.String intent = "";
	    
	    // optional string uri = 14;
	    public java.lang.String uri = "";
	    
	    // optional int32 iconType = 15;
	    public int iconType = 0;
	    
	    // optional string iconPackage = 16;
	    public java.lang.String iconPackage = "";
	    
	    // optional string iconResource = 17;
	    public java.lang.String iconResource = "";
	    
	    // optional bytes icon = 18;
	    public byte[] icon = com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES;
	    
	    public final Favorite clear() {
	      id = 0L;
	      itemType = 0;
	      title = "";
	      container = 0;
	      screen = 0;
	      cellX = 0;
	      cellY = 0;
	      spanX = 0;
	      spanY = 0;
	      displayMode = 0;
	      appWidgetId = 0;
	      appWidgetProvider = "";
	      intent = "";
	      uri = "";
	      iconType = 0;
	      iconPackage = "";
	      iconResource = "";
	      icon = com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES;
	      cachedSize = -1;
	      return this;
	    }
	    
	    @Override
	    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
	                        throws java.io.IOException {
	      output.writeInt64(1, this.id);
	      output.writeInt32(2, this.itemType);
	      if (!this.title.equals("")) {
	        output.writeString(3, this.title);
	      }
	      if (this.container != 0) {
	        output.writeInt32(4, this.container);
	      }
	      if (this.screen != 0) {
	        output.writeInt32(5, this.screen);
	      }
	      if (this.cellX != 0) {
	        output.writeInt32(6, this.cellX);
	      }
	      if (this.cellY != 0) {
	        output.writeInt32(7, this.cellY);
	      }
	      if (this.spanX != 0) {
	        output.writeInt32(8, this.spanX);
	      }
	      if (this.spanY != 0) {
	        output.writeInt32(9, this.spanY);
	      }
	      if (this.displayMode != 0) {
	        output.writeInt32(10, this.displayMode);
	      }
	      if (this.appWidgetId != 0) {
	        output.writeInt32(11, this.appWidgetId);
	      }
	      if (!this.appWidgetProvider.equals("")) {
	        output.writeString(12, this.appWidgetProvider);
	      }
	      if (!this.intent.equals("")) {
	        output.writeString(13, this.intent);
	      }
	      if (!this.uri.equals("")) {
	        output.writeString(14, this.uri);
	      }
	      if (this.iconType != 0) {
	        output.writeInt32(15, this.iconType);
	      }
	      if (!this.iconPackage.equals("")) {
	        output.writeString(16, this.iconPackage);
	      }
	      if (!this.iconResource.equals("")) {
	        output.writeString(17, this.iconResource);
	      }
	      if (!java.util.Arrays.equals(this.icon, com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES)) {
	        output.writeBytes(18, this.icon);
	      }
	    }
	    
	    private int cachedSize = -1;
	    @Override
	    public int getCachedSize() {
	      if (cachedSize < 0) {
	        // getSerializedSize sets cachedSize
	        getSerializedSize();
	      }
	      return cachedSize;
	    }
	    
	    @Override
	    public int getSerializedSize() {
	      int size = 0;
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeInt64Size(1, this.id);
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeInt32Size(2, this.itemType);
	      if (!this.title.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(3, this.title);
	      }
	      if (this.container != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(4, this.container);
	      }
	      if (this.screen != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(5, this.screen);
	      }
	      if (this.cellX != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(6, this.cellX);
	      }
	      if (this.cellY != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(7, this.cellY);
	      }
	      if (this.spanX != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(8, this.spanX);
	      }
	      if (this.spanY != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(9, this.spanY);
	      }
	      if (this.displayMode != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(10, this.displayMode);
	      }
	      if (this.appWidgetId != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(11, this.appWidgetId);
	      }
	      if (!this.appWidgetProvider.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(12, this.appWidgetProvider);
	      }
	      if (!this.intent.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(13, this.intent);
	      }
	      if (!this.uri.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(14, this.uri);
	      }
	      if (this.iconType != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(15, this.iconType);
	      }
	      if (!this.iconPackage.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(16, this.iconPackage);
	      }
	      if (!this.iconResource.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(17, this.iconResource);
	      }
	      if (!java.util.Arrays.equals(this.icon, com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES)) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeBytesSize(18, this.icon);
	      }
	      cachedSize = size;
	      return size;
	    }
	    
	    @Override
	    public Favorite mergeFrom(
	        com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      while (true) {
	        int tag = input.readTag();
	        switch (tag) {
	          case 0:
	            return this;
	          default: {
	            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
	              return this;
	            }
	            break;
	          }
	          case 8: {
	            this.id = input.readInt64();
	            break;
	          }
	          case 16: {
	            this.itemType = input.readInt32();
	            break;
	          }
	          case 26: {
	            this.title = input.readString();
	            break;
	          }
	          case 32: {
	            this.container = input.readInt32();
	            break;
	          }
	          case 40: {
	            this.screen = input.readInt32();
	            break;
	          }
	          case 48: {
	            this.cellX = input.readInt32();
	            break;
	          }
	          case 56: {
	            this.cellY = input.readInt32();
	            break;
	          }
	          case 64: {
	            this.spanX = input.readInt32();
	            break;
	          }
	          case 72: {
	            this.spanY = input.readInt32();
	            break;
	          }
	          case 80: {
	            this.displayMode = input.readInt32();
	            break;
	          }
	          case 88: {
	            this.appWidgetId = input.readInt32();
	            break;
	          }
	          case 98: {
	            this.appWidgetProvider = input.readString();
	            break;
	          }
	          case 106: {
	            this.intent = input.readString();
	            break;
	          }
	          case 114: {
	            this.uri = input.readString();
	            break;
	          }
	          case 120: {
	            this.iconType = input.readInt32();
	            break;
	          }
	          case 130: {
	            this.iconPackage = input.readString();
	            break;
	          }
	          case 138: {
	            this.iconResource = input.readString();
	            break;
	          }
	          case 146: {
	            this.icon = input.readBytes();
	            break;
	          }
	        }
	      }
	    }
	    
	    public static Favorite parseFrom(byte[] data)
	        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
	      return com.google.protobuf.nano.MessageNano.mergeFrom(new Favorite(), data);
	    }
	    
	    public static Favorite parseFrom(
	            com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      return new Favorite().mergeFrom(input);
	    }
	    
	  }
	  
	  @SuppressWarnings("hiding")
	  public static final class Screen extends
	      com.google.protobuf.nano.MessageNano {
	    public static final Screen EMPTY_ARRAY[] = {};
	    public Screen() {}
	    
	    // required int64 id = 1;
	    public long id = 0L;
	    
	    // optional int32 rank = 2;
	    public int rank = 0;
	    
	    public final Screen clear() {
	      id = 0L;
	      rank = 0;
	      cachedSize = -1;
	      return this;
	    }
	    
	    @Override
	    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
	                        throws java.io.IOException {
	      output.writeInt64(1, this.id);
	      if (this.rank != 0) {
	        output.writeInt32(2, this.rank);
	      }
	    }
	    
	    private int cachedSize = -1;
	    @Override
	    public int getCachedSize() {
	      if (cachedSize < 0) {
	        // getSerializedSize sets cachedSize
	        getSerializedSize();
	      }
	      return cachedSize;
	    }
	    
	    @Override
	    public int getSerializedSize() {
	      int size = 0;
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeInt64Size(1, this.id);
	      if (this.rank != 0) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeInt32Size(2, this.rank);
	      }
	      cachedSize = size;
	      return size;
	    }
	    
	    @Override
	    public Screen mergeFrom(
	        com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      while (true) {
	        int tag = input.readTag();
	        switch (tag) {
	          case 0:
	            return this;
	          default: {
	            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
	              return this;
	            }
	            break;
	          }
	          case 8: {
	            this.id = input.readInt64();
	            break;
	          }
	          case 16: {
	            this.rank = input.readInt32();
	            break;
	          }
	        }
	      }
	    }
	    
	    public static Screen parseFrom(byte[] data)
	        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
	      return com.google.protobuf.nano.MessageNano.mergeFrom(new Screen(), data);
	    }
	    
	    public static Screen parseFrom(
	            com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      return new Screen().mergeFrom(input);
	    }
	    
	  }
	  
	  @SuppressWarnings("hiding")
	  public static final class Resource extends
	      com.google.protobuf.nano.MessageNano {
	    public static final Resource EMPTY_ARRAY[] = {};
	    public Resource() {}
	    
	    // required int32 dpi = 1;
	    public int dpi = 0;
	    
	    // required bytes data = 2;
	    public byte[] data = com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES;
	    
	    public final Resource clear() {
	      dpi = 0;
	      data = com.google.protobuf.nano.WireFormatNano.EMPTY_BYTES;
	      cachedSize = -1;
	      return this;
	    }
	    
	    @Override
	    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
	                        throws java.io.IOException {
	      output.writeInt32(1, this.dpi);
	      output.writeBytes(2, this.data);
	    }
	    
	    private int cachedSize = -1;
	    @Override
	    public int getCachedSize() {
	      if (cachedSize < 0) {
	        // getSerializedSize sets cachedSize
	        getSerializedSize();
	      }
	      return cachedSize;
	    }
	    
	    @Override
	    public int getSerializedSize() {
	      int size = 0;
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeInt32Size(1, this.dpi);
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeBytesSize(2, this.data);
	      cachedSize = size;
	      return size;
	    }
	    
	    @Override
	    public Resource mergeFrom(
	        com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      while (true) {
	        int tag = input.readTag();
	        switch (tag) {
	          case 0:
	            return this;
	          default: {
	            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
	              return this;
	            }
	            break;
	          }
	          case 8: {
	            this.dpi = input.readInt32();
	            break;
	          }
	          case 18: {
	            this.data = input.readBytes();
	            break;
	          }
	        }
	      }
	    }
	    
	    public static Resource parseFrom(byte[] data)
	        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
	      return com.google.protobuf.nano.MessageNano.mergeFrom(new Resource(), data);
	    }
	    
	    public static Resource parseFrom(
	            com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      return new Resource().mergeFrom(input);
	    }
	    
	  }
	  
	  @SuppressWarnings("hiding")
	  public static final class Widget extends
	      com.google.protobuf.nano.MessageNano {
	    public static final Widget EMPTY_ARRAY[] = {};
	    public Widget() {}
	    
	    // required string provider = 1;
	    public java.lang.String provider = "";
	    
	    // optional string label = 2;
	    public java.lang.String label = "";
	    
	    // optional bool configure = 3;
	    public boolean configure = false;
	    
	    // optional .launcher_backup.Resource icon = 4;
	    public com.phonemetra.turbo.launcher.backup.BackupProtos.Resource icon = null;
	    
	    // optional .launcher_backup.Resource preview = 5;
	    public com.phonemetra.turbo.launcher.backup.BackupProtos.Resource preview = null;
	    
	    public final Widget clear() {
	      provider = "";
	      label = "";
	      configure = false;
	      icon = null;
	      preview = null;
	      cachedSize = -1;
	      return this;
	    }
	    
	    @Override
	    public void writeTo(com.google.protobuf.nano.CodedOutputByteBufferNano output)
	                        throws java.io.IOException {
	      output.writeString(1, this.provider);
	      if (!this.label.equals("")) {
	        output.writeString(2, this.label);
	      }
	      if (this.configure != false) {
	        output.writeBool(3, this.configure);
	      }
	      if (this.icon != null) {
	        output.writeMessage(4, this.icon);
	      }
	      if (this.preview != null) {
	        output.writeMessage(5, this.preview);
	      }
	    }
	    
	    private int cachedSize = -1;
	    @Override
	    public int getCachedSize() {
	      if (cachedSize < 0) {
	        // getSerializedSize sets cachedSize
	        getSerializedSize();
	      }
	      return cachedSize;
	    }
	    
	    @Override
	    public int getSerializedSize() {
	      int size = 0;
	      size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeStringSize(1, this.provider);
	      if (!this.label.equals("")) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeStringSize(2, this.label);
	      }
	      if (this.configure != false) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	            .computeBoolSize(3, this.configure);
	      }
	      if (this.icon != null) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeMessageSize(4, this.icon);
	      }
	      if (this.preview != null) {
	        size += com.google.protobuf.nano.CodedOutputByteBufferNano
	          .computeMessageSize(5, this.preview);
	      }
	      cachedSize = size;
	      return size;
	    }
	    
	    @Override
	    public Widget mergeFrom(
	        com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      while (true) {
	        int tag = input.readTag();
	        switch (tag) {
	          case 0:
	            return this;
	          default: {
	            if (!com.google.protobuf.nano.WireFormatNano.parseUnknownField(input, tag)) {
	              return this;
	            }
	            break;
	          }
	          case 10: {
	            this.provider = input.readString();
	            break;
	          }
	          case 18: {
	            this.label = input.readString();
	            break;
	          }
	          case 24: {
	            this.configure = input.readBool();
	            break;
	          }
	          case 34: {
	            this.icon = new com.phonemetra.turbo.launcher.backup.BackupProtos.Resource();
	            input.readMessage(this.icon);
	            break;
	          }
	          case 42: {
	            this.preview = new com.phonemetra.turbo.launcher.backup.BackupProtos.Resource();
	            input.readMessage(this.preview);
	            break;
	          }
	        }
	      }
	    }
	    
	    public static Widget parseFrom(byte[] data)
	        throws com.google.protobuf.nano.InvalidProtocolBufferNanoException {
	      return com.google.protobuf.nano.MessageNano.mergeFrom(new Widget(), data);
	    }
	    
	    public static Widget parseFrom(
	            com.google.protobuf.nano.CodedInputByteBufferNano input)
	        throws java.io.IOException {
	      return new Widget().mergeFrom(input);
	    }
	    
	  }
	  
} 
	  